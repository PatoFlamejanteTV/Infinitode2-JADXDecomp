package com.badlogic.gdx.ai.btree.branch;

import com.badlogic.gdx.ai.btree.BranchTask;
import com.badlogic.gdx.ai.btree.Task;
import com.badlogic.gdx.ai.btree.annotation.TaskAttribute;
import com.badlogic.gdx.utils.Array;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/btree/branch/Parallel.class */
public class Parallel<E> extends BranchTask<E> {

    @TaskAttribute
    public Policy policy;

    @TaskAttribute
    public Orchestrator orchestrator;
    private boolean noRunningTasks;
    private Boolean lastResult;
    private int currentChildIndex;

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/btree/branch/Parallel$Orchestrator.class */
    public enum Orchestrator {
        Resume { // from class: com.badlogic.gdx.ai.btree.branch.Parallel.Orchestrator.1
            @Override // com.badlogic.gdx.ai.btree.branch.Parallel.Orchestrator
            public final void execute(Parallel<?> parallel) {
                ((Parallel) parallel).noRunningTasks = true;
                ((Parallel) parallel).lastResult = null;
                ((Parallel) parallel).currentChildIndex = 0;
                while (((Parallel) parallel).currentChildIndex < ((Parallel) parallel).children.size) {
                    Task task = (Task) ((Parallel) parallel).children.get(((Parallel) parallel).currentChildIndex);
                    if (task.getStatus() == Task.Status.RUNNING) {
                        task.run();
                    } else {
                        task.setControl(parallel);
                        task.start();
                        if (task.checkGuard(parallel)) {
                            task.run();
                        } else {
                            task.fail();
                        }
                    }
                    if (((Parallel) parallel).lastResult != null) {
                        parallel.cancelRunningChildren(((Parallel) parallel).noRunningTasks ? ((Parallel) parallel).currentChildIndex + 1 : 0);
                        if (((Parallel) parallel).lastResult.booleanValue()) {
                            parallel.success();
                            return;
                        } else {
                            parallel.fail();
                            return;
                        }
                    }
                    Parallel.access$308(parallel);
                }
                parallel.running();
            }
        },
        Join { // from class: com.badlogic.gdx.ai.btree.branch.Parallel.Orchestrator.2
            /* JADX WARN: Removed duplicated region for block: B:13:0x00ae A[LOOP:0: B:2:0x0012->B:13:0x00ae, LOOP_END] */
            /* JADX WARN: Removed duplicated region for block: B:14:0x0081 A[SYNTHETIC] */
            @Override // com.badlogic.gdx.ai.btree.branch.Parallel.Orchestrator
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final void execute(com.badlogic.gdx.ai.btree.branch.Parallel<?> r5) {
                /*
                    r4 = this;
                    r0 = r5
                    r1 = 1
                    boolean r0 = com.badlogic.gdx.ai.btree.branch.Parallel.access$102(r0, r1)
                    r0 = r5
                    r1 = 0
                    java.lang.Boolean r0 = com.badlogic.gdx.ai.btree.branch.Parallel.access$202(r0, r1)
                    r0 = r5
                    r1 = 0
                    int r0 = com.badlogic.gdx.ai.btree.branch.Parallel.access$302(r0, r1)
                L12:
                    r0 = r5
                    int r0 = com.badlogic.gdx.ai.btree.branch.Parallel.access$300(r0)
                    r1 = r5
                    com.badlogic.gdx.utils.Array r1 = com.badlogic.gdx.ai.btree.branch.Parallel.access$700(r1)
                    int r1 = r1.size
                    if (r0 >= r1) goto Lb6
                    r0 = r5
                    com.badlogic.gdx.utils.Array r0 = com.badlogic.gdx.ai.btree.branch.Parallel.access$800(r0)
                    r1 = r5
                    int r1 = com.badlogic.gdx.ai.btree.branch.Parallel.access$300(r1)
                    java.lang.Object r0 = r0.get(r1)
                    com.badlogic.gdx.ai.btree.Task r0 = (com.badlogic.gdx.ai.btree.Task) r0
                    r6 = r0
                    int[] r0 = com.badlogic.gdx.ai.btree.branch.Parallel.AnonymousClass1.$SwitchMap$com$badlogic$gdx$ai$btree$Task$Status
                    r1 = r6
                    com.badlogic.gdx.ai.btree.Task$Status r1 = r1.getStatus()
                    int r1 = r1.ordinal()
                    r0 = r0[r1]
                    switch(r0) {
                        case 1: goto L54;
                        case 2: goto L5b;
                        case 3: goto L5b;
                        default: goto L5e;
                    }
                L54:
                    r0 = r6
                    r0.run()
                    goto L7a
                L5b:
                    goto L7a
                L5e:
                    r0 = r6
                    r1 = r5
                    r0.setControl(r1)
                    r0 = r6
                    r0.start()
                    r0 = r6
                    r1 = r5
                    boolean r0 = r0.checkGuard(r1)
                    if (r0 == 0) goto L76
                    r0 = r6
                    r0.run()
                    goto L7a
                L76:
                    r0 = r6
                    r0.fail()
                L7a:
                    r0 = r5
                    java.lang.Boolean r0 = com.badlogic.gdx.ai.btree.branch.Parallel.access$200(r0)
                    if (r0 == 0) goto Lae
                    r0 = r5
                    r1 = r0
                    boolean r1 = com.badlogic.gdx.ai.btree.branch.Parallel.access$100(r1)
                    if (r1 == 0) goto L92
                    r1 = r5
                    int r1 = com.badlogic.gdx.ai.btree.branch.Parallel.access$300(r1)
                    r2 = 1
                    int r1 = r1 + r2
                    goto L93
                L92:
                    r1 = 0
                L93:
                    com.badlogic.gdx.ai.btree.branch.Parallel.access$900(r0, r1)
                    r0 = r5
                    r0.resetAllChildren()
                    r0 = r5
                    java.lang.Boolean r0 = com.badlogic.gdx.ai.btree.branch.Parallel.access$200(r0)
                    boolean r0 = r0.booleanValue()
                    if (r0 == 0) goto La9
                    r0 = r5
                    r0.success()
                    return
                La9:
                    r0 = r5
                    r0.fail()
                    return
                Lae:
                    r0 = r5
                    int r0 = com.badlogic.gdx.ai.btree.branch.Parallel.access$308(r0)
                    goto L12
                Lb6:
                    r0 = r5
                    r0.running()
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.badlogic.gdx.ai.btree.branch.Parallel.Orchestrator.AnonymousClass2.execute(com.badlogic.gdx.ai.btree.branch.Parallel):void");
            }
        };

        public abstract void execute(Parallel<?> parallel);
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/btree/branch/Parallel$Policy.class */
    public enum Policy {
        Sequence { // from class: com.badlogic.gdx.ai.btree.branch.Parallel.Policy.1
            @Override // com.badlogic.gdx.ai.btree.branch.Parallel.Policy
            public final Boolean onChildSuccess(Parallel<?> parallel) {
                switch (parallel.orchestrator) {
                    case Join:
                        if (((Parallel) parallel).noRunningTasks && ((Task) ((Parallel) parallel).children.get(((Parallel) parallel).children.size - 1)).getStatus() == Task.Status.SUCCEEDED) {
                            return Boolean.TRUE;
                        }
                        return null;
                    default:
                        if (((Parallel) parallel).noRunningTasks && ((Parallel) parallel).currentChildIndex == ((Parallel) parallel).children.size - 1) {
                            return Boolean.TRUE;
                        }
                        return null;
                }
            }

            @Override // com.badlogic.gdx.ai.btree.branch.Parallel.Policy
            public final Boolean onChildFail(Parallel<?> parallel) {
                return Boolean.FALSE;
            }
        },
        Selector { // from class: com.badlogic.gdx.ai.btree.branch.Parallel.Policy.2
            @Override // com.badlogic.gdx.ai.btree.branch.Parallel.Policy
            public final Boolean onChildSuccess(Parallel<?> parallel) {
                return Boolean.TRUE;
            }

            @Override // com.badlogic.gdx.ai.btree.branch.Parallel.Policy
            public final Boolean onChildFail(Parallel<?> parallel) {
                if (((Parallel) parallel).noRunningTasks && ((Parallel) parallel).currentChildIndex == ((Parallel) parallel).children.size - 1) {
                    return Boolean.FALSE;
                }
                return null;
            }
        };

        public abstract Boolean onChildSuccess(Parallel<?> parallel);

        public abstract Boolean onChildFail(Parallel<?> parallel);
    }

    static /* synthetic */ int access$308(Parallel parallel) {
        int i = parallel.currentChildIndex;
        parallel.currentChildIndex = i + 1;
        return i;
    }

    public Parallel() {
        this(new Array());
    }

    public Parallel(Task<E>... taskArr) {
        this(new Array(taskArr));
    }

    public Parallel(Array<Task<E>> array) {
        this(Policy.Sequence, array);
    }

    public Parallel(Policy policy) {
        this(policy, new Array());
    }

    public Parallel(Policy policy, Task<E>... taskArr) {
        this(policy, new Array(taskArr));
    }

    public Parallel(Policy policy, Array<Task<E>> array) {
        this(policy, Orchestrator.Resume, array);
    }

    public Parallel(Orchestrator orchestrator, Array<Task<E>> array) {
        this(Policy.Sequence, orchestrator, array);
    }

    public Parallel(Orchestrator orchestrator, Task<E>... taskArr) {
        this(Policy.Sequence, orchestrator, new Array(taskArr));
    }

    public Parallel(Policy policy, Orchestrator orchestrator, Array<Task<E>> array) {
        super(array);
        this.policy = policy;
        this.orchestrator = orchestrator;
        this.noRunningTasks = true;
    }

    @Override // com.badlogic.gdx.ai.btree.Task
    public void run() {
        this.orchestrator.execute(this);
    }

    @Override // com.badlogic.gdx.ai.btree.Task
    public void childRunning(Task<E> task, Task<E> task2) {
        this.noRunningTasks = false;
    }

    @Override // com.badlogic.gdx.ai.btree.Task
    public void childSuccess(Task<E> task) {
        this.lastResult = this.policy.onChildSuccess(this);
    }

    @Override // com.badlogic.gdx.ai.btree.Task
    public void childFail(Task<E> task) {
        this.lastResult = this.policy.onChildFail(this);
    }

    @Override // com.badlogic.gdx.ai.btree.Task
    public void resetTask() {
        super.resetTask();
        this.noRunningTasks = true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.badlogic.gdx.ai.btree.BranchTask, com.badlogic.gdx.ai.btree.Task
    public Task<E> copyTo(Task<E> task) {
        Parallel parallel = (Parallel) task;
        parallel.policy = this.policy;
        parallel.orchestrator = this.orchestrator;
        return super.copyTo(task);
    }

    public void resetAllChildren() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            getChild(i).reset();
        }
    }

    @Override // com.badlogic.gdx.ai.btree.BranchTask, com.badlogic.gdx.ai.btree.Task, com.badlogic.gdx.utils.Pool.Poolable
    public void reset() {
        this.policy = Policy.Sequence;
        this.orchestrator = Orchestrator.Resume;
        this.noRunningTasks = true;
        this.lastResult = null;
        this.currentChildIndex = 0;
        super.reset();
    }
}
