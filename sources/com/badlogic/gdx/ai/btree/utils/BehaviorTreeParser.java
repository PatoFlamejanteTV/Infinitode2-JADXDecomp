package com.badlogic.gdx.ai.btree.utils;

import com.badlogic.gdx.ai.GdxAI;
import com.badlogic.gdx.ai.btree.BehaviorTree;
import com.badlogic.gdx.ai.btree.Task;
import com.badlogic.gdx.ai.btree.annotation.TaskAttribute;
import com.badlogic.gdx.ai.btree.annotation.TaskConstraint;
import com.badlogic.gdx.ai.btree.branch.DynamicGuardSelector;
import com.badlogic.gdx.ai.btree.branch.Parallel;
import com.badlogic.gdx.ai.btree.branch.RandomSelector;
import com.badlogic.gdx.ai.btree.branch.RandomSequence;
import com.badlogic.gdx.ai.btree.branch.Selector;
import com.badlogic.gdx.ai.btree.branch.Sequence;
import com.badlogic.gdx.ai.btree.decorator.AlwaysFail;
import com.badlogic.gdx.ai.btree.decorator.AlwaysSucceed;
import com.badlogic.gdx.ai.btree.decorator.Include;
import com.badlogic.gdx.ai.btree.decorator.Invert;
import com.badlogic.gdx.ai.btree.decorator.Random;
import com.badlogic.gdx.ai.btree.decorator.Repeat;
import com.badlogic.gdx.ai.btree.decorator.SemaphoreGuard;
import com.badlogic.gdx.ai.btree.decorator.UntilFail;
import com.badlogic.gdx.ai.btree.decorator.UntilSuccess;
import com.badlogic.gdx.ai.btree.leaf.Failure;
import com.badlogic.gdx.ai.btree.leaf.Success;
import com.badlogic.gdx.ai.btree.leaf.Wait;
import com.badlogic.gdx.ai.utils.random.Distribution;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectSet;
import com.badlogic.gdx.utils.reflect.Annotation;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.Field;
import com.badlogic.gdx.utils.reflect.ReflectionException;
import com.vladsch.flexmark.util.html.Attribute;
import java.io.InputStream;
import java.io.Reader;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/btree/utils/BehaviorTreeParser.class */
public class BehaviorTreeParser<E> {
    public static final int DEBUG_NONE = 0;
    public static final int DEBUG_LOW = 1;
    public static final int DEBUG_HIGH = 2;
    private static final String TAG = "BehaviorTreeParser";
    public int debugLevel;
    public DistributionAdapters distributionAdapters;
    private DefaultBehaviorTreeReader<E> btReader;

    public BehaviorTreeParser() {
        this(0);
    }

    public BehaviorTreeParser(DistributionAdapters distributionAdapters) {
        this(distributionAdapters, 0);
    }

    public BehaviorTreeParser(int i) {
        this(new DistributionAdapters(), i);
    }

    public BehaviorTreeParser(DistributionAdapters distributionAdapters, int i) {
        this(distributionAdapters, i, null);
    }

    public BehaviorTreeParser(DistributionAdapters distributionAdapters, int i, DefaultBehaviorTreeReader<E> defaultBehaviorTreeReader) {
        this.distributionAdapters = distributionAdapters;
        this.debugLevel = i;
        this.btReader = defaultBehaviorTreeReader == null ? new DefaultBehaviorTreeReader<>() : defaultBehaviorTreeReader;
        this.btReader.setParser(this);
    }

    public BehaviorTree<E> parse(String str, E e) {
        this.btReader.parse(str);
        return createBehaviorTree(this.btReader.root, e);
    }

    public BehaviorTree<E> parse(InputStream inputStream, E e) {
        this.btReader.parse(inputStream);
        return createBehaviorTree(this.btReader.root, e);
    }

    public BehaviorTree<E> parse(FileHandle fileHandle, E e) {
        this.btReader.parse(fileHandle);
        return createBehaviorTree(this.btReader.root, e);
    }

    public BehaviorTree<E> parse(Reader reader, E e) {
        this.btReader.parse(reader);
        return createBehaviorTree(this.btReader.root, e);
    }

    protected BehaviorTree<E> createBehaviorTree(Task<E> task, E e) {
        if (this.debugLevel > 1) {
            printTree(task, 0);
        }
        return new BehaviorTree<>(task, e);
    }

    protected static <E> void printTree(Task<E> task, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            System.out.print(' ');
        }
        if (task.getGuard() != null) {
            System.out.println("Guard");
            i += 2;
            printTree(task.getGuard(), i);
            for (int i3 = 0; i3 < i; i3++) {
                System.out.print(' ');
            }
        }
        System.out.println(task.getClass().getSimpleName());
        for (int i4 = 0; i4 < task.getChildCount(); i4++) {
            printTree(task.getChild(i4), i + 2);
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/btree/utils/BehaviorTreeParser$DefaultBehaviorTreeReader.class */
    public static class DefaultBehaviorTreeReader<E> extends BehaviorTreeReader {
        private static final ObjectMap<String, String> DEFAULT_IMPORTS = new ObjectMap<>();
        protected BehaviorTreeParser<E> btParser;
        ObjectMap<Class<?>, Metadata> metadataCache;
        Task<E> root;
        String subtreeName;
        Statement statement;
        private int indent;
        ObjectMap<String, String> userImports;
        ObjectMap<String, Subtree<E>> subtrees;
        Subtree<E> currentTree;
        int currentTreeStartIndent;
        int currentDepth;
        int step;
        boolean isSubtreeRef;
        protected StackedTask<E> prevTask;
        protected StackedTask<E> guardChain;
        protected Array<StackedTask<E>> stack;
        ObjectSet<String> encounteredAttributes;
        boolean isGuard;

        static {
            Class[] clsArr = {AlwaysFail.class, AlwaysSucceed.class, DynamicGuardSelector.class, Failure.class, Include.class, Invert.class, Parallel.class, Random.class, RandomSelector.class, RandomSequence.class, Repeat.class, Selector.class, SemaphoreGuard.class, Sequence.class, Success.class, UntilFail.class, UntilSuccess.class, Wait.class};
            for (int i = 0; i < 18; i++) {
                Class cls = clsArr[i];
                String name = cls.getName();
                String simpleName = cls.getSimpleName();
                DEFAULT_IMPORTS.put(Character.toLowerCase(simpleName.charAt(0)) + (simpleName.length() > 1 ? simpleName.substring(1) : ""), name);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/btree/utils/BehaviorTreeParser$DefaultBehaviorTreeReader$Statement.class */
        public enum Statement {
            Import("import") { // from class: com.badlogic.gdx.ai.btree.utils.BehaviorTreeParser.DefaultBehaviorTreeReader.Statement.1
                @Override // com.badlogic.gdx.ai.btree.utils.BehaviorTreeParser.DefaultBehaviorTreeReader.Statement
                protected final <E> void enter(DefaultBehaviorTreeReader<E> defaultBehaviorTreeReader, String str, boolean z) {
                }

                @Override // com.badlogic.gdx.ai.btree.utils.BehaviorTreeParser.DefaultBehaviorTreeReader.Statement
                protected final <E> boolean attribute(DefaultBehaviorTreeReader<E> defaultBehaviorTreeReader, String str, Object obj) {
                    if (!(obj instanceof String)) {
                        defaultBehaviorTreeReader.throwAttributeTypeException(this.name, str, "String");
                    }
                    defaultBehaviorTreeReader.addImport(str, (String) obj);
                    return true;
                }

                @Override // com.badlogic.gdx.ai.btree.utils.BehaviorTreeParser.DefaultBehaviorTreeReader.Statement
                protected final <E> void exit(DefaultBehaviorTreeReader<E> defaultBehaviorTreeReader) {
                }
            },
            Subtree("subtree") { // from class: com.badlogic.gdx.ai.btree.utils.BehaviorTreeParser.DefaultBehaviorTreeReader.Statement.2
                @Override // com.badlogic.gdx.ai.btree.utils.BehaviorTreeParser.DefaultBehaviorTreeReader.Statement
                protected final <E> void enter(DefaultBehaviorTreeReader<E> defaultBehaviorTreeReader, String str, boolean z) {
                }

                @Override // com.badlogic.gdx.ai.btree.utils.BehaviorTreeParser.DefaultBehaviorTreeReader.Statement
                protected final <E> boolean attribute(DefaultBehaviorTreeReader<E> defaultBehaviorTreeReader, String str, Object obj) {
                    if (!str.equals(Attribute.NAME_ATTR)) {
                        defaultBehaviorTreeReader.throwAttributeNameException(this.name, str, Attribute.NAME_ATTR);
                    }
                    if (!(obj instanceof String)) {
                        defaultBehaviorTreeReader.throwAttributeTypeException(this.name, str, "String");
                    }
                    if ("".equals(obj)) {
                        throw new GdxRuntimeException(this.name + ": the name connot be empty");
                    }
                    if (defaultBehaviorTreeReader.subtreeName != null) {
                        throw new GdxRuntimeException(this.name + ": the name has been already specified");
                    }
                    defaultBehaviorTreeReader.subtreeName = (String) obj;
                    return true;
                }

                @Override // com.badlogic.gdx.ai.btree.utils.BehaviorTreeParser.DefaultBehaviorTreeReader.Statement
                protected final <E> void exit(DefaultBehaviorTreeReader<E> defaultBehaviorTreeReader) {
                    if (defaultBehaviorTreeReader.subtreeName == null) {
                        throw new GdxRuntimeException(this.name + ": the name has not been specified");
                    }
                    defaultBehaviorTreeReader.switchToNewTree(defaultBehaviorTreeReader.subtreeName);
                    defaultBehaviorTreeReader.subtreeName = null;
                }
            },
            Root("root") { // from class: com.badlogic.gdx.ai.btree.utils.BehaviorTreeParser.DefaultBehaviorTreeReader.Statement.3
                @Override // com.badlogic.gdx.ai.btree.utils.BehaviorTreeParser.DefaultBehaviorTreeReader.Statement
                protected final <E> void enter(DefaultBehaviorTreeReader<E> defaultBehaviorTreeReader, String str, boolean z) {
                    defaultBehaviorTreeReader.subtreeName = "";
                }

                @Override // com.badlogic.gdx.ai.btree.utils.BehaviorTreeParser.DefaultBehaviorTreeReader.Statement
                protected final <E> boolean attribute(DefaultBehaviorTreeReader<E> defaultBehaviorTreeReader, String str, Object obj) {
                    defaultBehaviorTreeReader.throwAttributeTypeException(this.name, str, null);
                    return true;
                }

                @Override // com.badlogic.gdx.ai.btree.utils.BehaviorTreeParser.DefaultBehaviorTreeReader.Statement
                protected final <E> void exit(DefaultBehaviorTreeReader<E> defaultBehaviorTreeReader) {
                    defaultBehaviorTreeReader.switchToNewTree(defaultBehaviorTreeReader.subtreeName);
                    defaultBehaviorTreeReader.subtreeName = null;
                }
            },
            TreeTask(null) { // from class: com.badlogic.gdx.ai.btree.utils.BehaviorTreeParser.DefaultBehaviorTreeReader.Statement.4
                @Override // com.badlogic.gdx.ai.btree.utils.BehaviorTreeParser.DefaultBehaviorTreeReader.Statement
                protected final <E> void enter(DefaultBehaviorTreeReader<E> defaultBehaviorTreeReader, String str, boolean z) {
                    if (defaultBehaviorTreeReader.currentTree == null) {
                        defaultBehaviorTreeReader.switchToNewTree("");
                        defaultBehaviorTreeReader.subtreeName = null;
                    }
                    defaultBehaviorTreeReader.openTask(str, z);
                }

                @Override // com.badlogic.gdx.ai.btree.utils.BehaviorTreeParser.DefaultBehaviorTreeReader.Statement
                protected final <E> boolean attribute(DefaultBehaviorTreeReader<E> defaultBehaviorTreeReader, String str, Object obj) {
                    StackedTask<E> currentTask = defaultBehaviorTreeReader.getCurrentTask();
                    AttrInfo attrInfo = currentTask.metadata.attributes.get(str);
                    if (attrInfo == null) {
                        return false;
                    }
                    if (!defaultBehaviorTreeReader.encounteredAttributes.add(str)) {
                        throw defaultBehaviorTreeReader.stackedTaskException(currentTask, "attribute '" + str + "' specified more than once");
                    }
                    defaultBehaviorTreeReader.setField(defaultBehaviorTreeReader.getField(currentTask.task.getClass(), attrInfo.fieldName), currentTask.task, obj);
                    return true;
                }

                @Override // com.badlogic.gdx.ai.btree.utils.BehaviorTreeParser.DefaultBehaviorTreeReader.Statement
                protected final <E> void exit(DefaultBehaviorTreeReader<E> defaultBehaviorTreeReader) {
                    if (!defaultBehaviorTreeReader.isSubtreeRef) {
                        defaultBehaviorTreeReader.checkRequiredAttributes(defaultBehaviorTreeReader.getCurrentTask());
                        defaultBehaviorTreeReader.encounteredAttributes.clear();
                    }
                }
            };

            String name;

            protected abstract <E> void enter(DefaultBehaviorTreeReader<E> defaultBehaviorTreeReader, String str, boolean z);

            protected abstract <E> boolean attribute(DefaultBehaviorTreeReader<E> defaultBehaviorTreeReader, String str, Object obj);

            protected abstract <E> void exit(DefaultBehaviorTreeReader<E> defaultBehaviorTreeReader);

            Statement(String str) {
                this.name = str;
            }
        }

        public DefaultBehaviorTreeReader() {
            this(false);
        }

        public DefaultBehaviorTreeReader(boolean z) {
            super(z);
            this.metadataCache = new ObjectMap<>();
            this.userImports = new ObjectMap<>();
            this.subtrees = new ObjectMap<>();
            this.stack = new Array<>();
            this.encounteredAttributes = new ObjectSet<>();
        }

        public BehaviorTreeParser<E> getParser() {
            return this.btParser;
        }

        public void setParser(BehaviorTreeParser<E> behaviorTreeParser) {
            this.btParser = behaviorTreeParser;
        }

        @Override // com.badlogic.gdx.ai.btree.utils.BehaviorTreeReader
        public void parse(char[] cArr, int i, int i2) {
            this.debug = this.btParser.debugLevel > 0;
            this.root = null;
            clear();
            super.parse(cArr, i, i2);
            popAndCheckMinChildren(0);
            Subtree<E> subtree = this.subtrees.get("");
            if (subtree == null) {
                throw new GdxRuntimeException("Missing root tree");
            }
            this.root = subtree.rootTask;
            if (this.root == null) {
                throw new GdxRuntimeException("The tree must have at least the root task");
            }
            clear();
        }

        @Override // com.badlogic.gdx.ai.btree.utils.BehaviorTreeReader
        protected void startLine(int i) {
            if (this.btParser.debugLevel > 1) {
                GdxAI.getLogger().debug(BehaviorTreeParser.TAG, this.lineNumber + ": <" + i + ">");
            }
            this.indent = i;
        }

        private Statement checkStatement(String str) {
            return str.equals(Statement.Import.name) ? Statement.Import : str.equals(Statement.Subtree.name) ? Statement.Subtree : str.equals(Statement.Root.name) ? Statement.Root : Statement.TreeTask;
        }

        @Override // com.badlogic.gdx.ai.btree.utils.BehaviorTreeReader
        protected void startStatement(String str, boolean z, boolean z2) {
            if (this.btParser.debugLevel > 1) {
                GdxAI.getLogger().debug(BehaviorTreeParser.TAG, (z2 ? " guard" : " task") + " name '" + str + "'");
            }
            this.isSubtreeRef = z;
            this.statement = z ? Statement.TreeTask : checkStatement(str);
            if (z2 && this.statement != Statement.TreeTask) {
                throw new GdxRuntimeException(str + ": only tree's tasks can be guarded");
            }
            this.statement.enter(this, str, z2);
        }

        @Override // com.badlogic.gdx.ai.btree.utils.BehaviorTreeReader
        protected void attribute(String str, Object obj) {
            if (this.btParser.debugLevel > 1) {
                GdxAI.getLogger().debug(BehaviorTreeParser.TAG, this.lineNumber + ": attribute '" + str + " : " + obj + "'");
            }
            if (!this.statement.attribute(this, str, obj)) {
                if (this.statement == Statement.TreeTask) {
                    throw stackedTaskException(getCurrentTask(), "unknown attribute '" + str + "'");
                }
                throw new GdxRuntimeException(this.statement.name + ": unknown attribute '" + str + "'");
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Field getField(Class<?> cls, String str) {
            try {
                return ClassReflection.getField(cls, str);
            } catch (ReflectionException e) {
                throw new GdxRuntimeException(e);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setField(Field field, Task<E> task, Object obj) {
            field.setAccessible(true);
            Object castValue = castValue(field, obj);
            if (castValue == null) {
                throwAttributeTypeException(getCurrentTask().name, field.getName(), field.getType().getSimpleName());
            }
            try {
                field.set(task, castValue);
            } catch (ReflectionException e) {
                throw new GdxRuntimeException(e);
            }
        }

        protected Object castValue(Field field, Object obj) {
            Class type = field.getType();
            Object obj2 = null;
            if (obj instanceof Number) {
                Number number = (Number) obj;
                if (type == Integer.TYPE || type == Integer.class) {
                    obj2 = Integer.valueOf(number.intValue());
                } else if (type == Float.TYPE || type == Float.class) {
                    obj2 = Float.valueOf(number.floatValue());
                } else if (type == Long.TYPE || type == Long.class) {
                    obj2 = Long.valueOf(number.longValue());
                } else if (type == Double.TYPE || type == Double.class) {
                    obj2 = Double.valueOf(number.doubleValue());
                } else if (type == Short.TYPE || type == Short.class) {
                    obj2 = Short.valueOf(number.shortValue());
                } else if (type == Byte.TYPE || type == Byte.class) {
                    obj2 = Byte.valueOf(number.byteValue());
                } else if (ClassReflection.isAssignableFrom(Distribution.class, type)) {
                    obj2 = this.btParser.distributionAdapters.toDistribution("constant," + number, type);
                }
            } else if (obj instanceof Boolean) {
                if (type == Boolean.TYPE || type == Boolean.class) {
                    obj2 = obj;
                }
            } else if (obj instanceof String) {
                String str = (String) obj;
                if (type == String.class) {
                    obj2 = obj;
                } else if (type == Character.TYPE || type == Character.class) {
                    if (str.length() != 1) {
                        throw new GdxRuntimeException("Invalid character '" + obj + "'");
                    }
                    obj2 = Character.valueOf(str.charAt(0));
                } else if (ClassReflection.isAssignableFrom(Distribution.class, type)) {
                    obj2 = this.btParser.distributionAdapters.toDistribution(str, type);
                } else if (ClassReflection.isAssignableFrom(Enum.class, type)) {
                    Enum[] enumArr = (Enum[]) type.getEnumConstants();
                    int i = 0;
                    int length = enumArr.length;
                    while (true) {
                        if (i >= length) {
                            break;
                        }
                        Enum r0 = enumArr[i];
                        if (!r0.name().equalsIgnoreCase(str)) {
                            i++;
                        } else {
                            obj2 = r0;
                            break;
                        }
                    }
                }
            }
            return obj2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void throwAttributeNameException(String str, String str2, String str3) {
            String str4 = " no attribute expected";
            if (str3 != null) {
                str4 = "expected '" + str3 + "' instead";
            }
            throw new GdxRuntimeException(str + ": attribute '" + str2 + "' unknown; " + str4);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void throwAttributeTypeException(String str, String str2, String str3) {
            throw new GdxRuntimeException(str + ": attribute '" + str2 + "' must be of type " + str3);
        }

        @Override // com.badlogic.gdx.ai.btree.utils.BehaviorTreeReader
        protected void endLine() {
        }

        @Override // com.badlogic.gdx.ai.btree.utils.BehaviorTreeReader
        protected void endStatement() {
            this.statement.exit(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void openTask(String str, boolean z) {
            Task<E> task;
            try {
                if (this.isSubtreeRef) {
                    task = subtreeRootTaskInstance(str);
                } else {
                    String str2 = getImport(str);
                    String str3 = str2;
                    if (str2 == null) {
                        str3 = str;
                    }
                    task = (Task) ClassReflection.newInstance(ClassReflection.forName(str3));
                }
                if (!this.currentTree.inited()) {
                    initCurrentTree(task, this.indent);
                    this.indent = 0;
                } else if (!z) {
                    StackedTask<E> prevTask = getPrevTask();
                    this.indent -= this.currentTreeStartIndent;
                    if (prevTask.task == this.currentTree.rootTask) {
                        this.step = this.indent;
                    }
                    if (this.indent > this.currentDepth) {
                        this.stack.add(prevTask);
                    } else if (this.indent <= this.currentDepth) {
                        popAndCheckMinChildren(this.stack.size - ((this.currentDepth - this.indent) / this.step));
                    }
                    StackedTask<E> peek = this.stack.peek();
                    int i = peek.metadata.maxChildren;
                    if (peek.task.getChildCount() >= i) {
                        throw stackedTaskException(peek, "max number of children exceeded (" + (peek.task.getChildCount() + 1) + " > " + i + ")");
                    }
                    peek.task.addChild(task);
                }
                updateCurrentTask(createStackedTask(str, task), this.indent, z);
            } catch (ReflectionException e) {
                throw new GdxRuntimeException("Cannot parse behavior tree!!!", e);
            }
        }

        private StackedTask<E> createStackedTask(String str, Task<E> task) {
            Metadata findMetadata = findMetadata(task.getClass());
            if (findMetadata != null) {
                return new StackedTask<>(this.lineNumber, str, task, findMetadata);
            }
            throw new GdxRuntimeException(str + ": @TaskConstraint annotation not found in '" + task.getClass().getSimpleName() + "' class hierarchy");
        }

        private Metadata findMetadata(Class<?> cls) {
            Annotation annotation;
            Metadata metadata = this.metadataCache.get(cls);
            Metadata metadata2 = metadata;
            if (metadata == null && (annotation = ClassReflection.getAnnotation(cls, TaskConstraint.class)) != null) {
                TaskConstraint taskConstraint = (TaskConstraint) annotation.getAnnotation(TaskConstraint.class);
                ObjectMap objectMap = new ObjectMap();
                for (Field field : ClassReflection.getFields(cls)) {
                    Annotation declaredAnnotation = field.getDeclaredAnnotation(TaskAttribute.class);
                    if (declaredAnnotation != null) {
                        AttrInfo attrInfo = new AttrInfo(field.getName(), (TaskAttribute) declaredAnnotation.getAnnotation(TaskAttribute.class));
                        objectMap.put(attrInfo.name, attrInfo);
                    }
                }
                metadata2 = new Metadata(taskConstraint.minChildren(), taskConstraint.maxChildren(), objectMap);
                this.metadataCache.put(cls, metadata2);
            }
            return metadata2;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/btree/utils/BehaviorTreeParser$DefaultBehaviorTreeReader$StackedTask.class */
        public static class StackedTask<E> {
            public int lineNumber;
            public String name;
            public Task<E> task;
            public Metadata metadata;

            StackedTask(int i, String str, Task<E> task, Metadata metadata) {
                this.lineNumber = i;
                this.name = str;
                this.task = task;
                this.metadata = metadata;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/btree/utils/BehaviorTreeParser$DefaultBehaviorTreeReader$Metadata.class */
        public static class Metadata {
            int minChildren;
            int maxChildren;
            ObjectMap<String, AttrInfo> attributes;

            Metadata(int i, int i2, ObjectMap<String, AttrInfo> objectMap) {
                this.minChildren = i < 0 ? 0 : i;
                this.maxChildren = i2 < 0 ? Integer.MAX_VALUE : i2;
                this.attributes = objectMap;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/btree/utils/BehaviorTreeParser$DefaultBehaviorTreeReader$AttrInfo.class */
        public static class AttrInfo {
            String name;
            String fieldName;
            boolean required;

            AttrInfo(String str, TaskAttribute taskAttribute) {
                this(taskAttribute.name(), str, taskAttribute.required());
            }

            AttrInfo(String str, String str2, boolean z) {
                this.name = (str == null || str.length() == 0) ? str2 : str;
                this.fieldName = str2;
                this.required = z;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/btree/utils/BehaviorTreeParser$DefaultBehaviorTreeReader$Subtree.class */
        public static class Subtree<E> {
            String name;
            Task<E> rootTask;
            int referenceCount;

            Subtree() {
                this(null);
            }

            Subtree(String str) {
                this.name = str;
                this.rootTask = null;
                this.referenceCount = 0;
            }

            public void init(Task<E> task) {
                this.rootTask = task;
            }

            public boolean inited() {
                return this.rootTask != null;
            }

            public boolean isRootTree() {
                return this.name == null || "".equals(this.name);
            }

            public Task<E> rootTaskInstance() {
                int i = this.referenceCount;
                this.referenceCount = i + 1;
                if (i == 0) {
                    return this.rootTask;
                }
                return this.rootTask.cloneTask();
            }
        }

        StackedTask<E> getLastStackedTask() {
            return this.stack.peek();
        }

        StackedTask<E> getPrevTask() {
            return this.prevTask;
        }

        StackedTask<E> getCurrentTask() {
            return this.isGuard ? this.guardChain : this.prevTask;
        }

        void updateCurrentTask(StackedTask<E> stackedTask, int i, boolean z) {
            this.isGuard = z;
            stackedTask.task.setGuard(this.guardChain == null ? null : this.guardChain.task);
            if (z) {
                this.guardChain = stackedTask;
                return;
            }
            this.prevTask = stackedTask;
            this.guardChain = null;
            this.currentDepth = i;
        }

        void clear() {
            this.prevTask = null;
            this.guardChain = null;
            this.currentTree = null;
            this.userImports.clear();
            this.subtrees.clear();
            this.stack.clear();
            this.encounteredAttributes.clear();
        }

        void switchToNewTree(String str) {
            popAndCheckMinChildren(0);
            this.currentTree = new Subtree<>(str);
            if (this.subtrees.put(str, this.currentTree) != null) {
                throw new GdxRuntimeException("A subtree named '" + str + "' is already defined");
            }
        }

        void initCurrentTree(Task<E> task, int i) {
            this.currentDepth = -1;
            this.step = 1;
            this.currentTreeStartIndent = i;
            this.currentTree.init(task);
            this.prevTask = null;
        }

        Task<E> subtreeRootTaskInstance(String str) {
            Subtree<E> subtree = this.subtrees.get(str);
            if (subtree == null) {
                throw new GdxRuntimeException("Undefined subtree with name '" + str + "'");
            }
            return subtree.rootTaskInstance();
        }

        void addImport(String str, String str2) {
            if (str2 == null) {
                throw new GdxRuntimeException("import: missing task class name.");
            }
            if (str == null) {
                try {
                    str = ClassReflection.forName(str2).getSimpleName();
                } catch (ReflectionException unused) {
                    throw new GdxRuntimeException("import: class not found '" + str2 + "'");
                }
            }
            if (getImport(str) != null) {
                throw new GdxRuntimeException("import: alias '" + str + "' previously defined already.");
            }
            this.userImports.put(str, str2);
        }

        String getImport(String str) {
            String str2 = DEFAULT_IMPORTS.get(str);
            return str2 != null ? str2 : this.userImports.get(str);
        }

        private void popAndCheckMinChildren(int i) {
            if (this.prevTask != null) {
                checkMinChildren(this.prevTask);
            }
            while (this.stack.size > i) {
                checkMinChildren(this.stack.pop());
            }
        }

        private void checkMinChildren(StackedTask<E> stackedTask) {
            int i = stackedTask.metadata.minChildren;
            if (stackedTask.task.getChildCount() < i) {
                throw stackedTaskException(stackedTask, "not enough children (" + stackedTask.task.getChildCount() + " < " + i + ")");
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void checkRequiredAttributes(StackedTask<E> stackedTask) {
            ObjectMap.Entries<String, AttrInfo> it = stackedTask.metadata.attributes.iterator();
            while (it.hasNext()) {
                ObjectMap.Entry<String, AttrInfo> next = it.next();
                if (next.value.required && !this.encounteredAttributes.contains(next.key)) {
                    throw stackedTaskException(stackedTask, "missing required attribute '" + next.key + "'");
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public GdxRuntimeException stackedTaskException(StackedTask<E> stackedTask, String str) {
            return new GdxRuntimeException(stackedTask.name + " at line " + stackedTask.lineNumber + ": " + str);
        }
    }
}
