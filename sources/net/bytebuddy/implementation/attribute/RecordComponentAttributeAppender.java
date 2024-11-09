package net.bytebuddy.implementation.attribute;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.annotation.AnnotationDescription;
import net.bytebuddy.description.type.RecordComponentDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.implementation.attribute.AnnotationAppender;
import net.bytebuddy.jar.asm.RecordComponentVisitor;
import net.bytebuddy.utility.nullability.MaybeNull;

/* loaded from: infinitode-2.jar:net/bytebuddy/implementation/attribute/RecordComponentAttributeAppender.class */
public interface RecordComponentAttributeAppender {
    void apply(RecordComponentVisitor recordComponentVisitor, RecordComponentDescription recordComponentDescription, AnnotationValueFilter annotationValueFilter);

    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/attribute/RecordComponentAttributeAppender$NoOp.class */
    public enum NoOp implements RecordComponentAttributeAppender, Factory {
        INSTANCE;

        @Override // net.bytebuddy.implementation.attribute.RecordComponentAttributeAppender.Factory
        public final RecordComponentAttributeAppender make(TypeDescription typeDescription) {
            return this;
        }

        @Override // net.bytebuddy.implementation.attribute.RecordComponentAttributeAppender
        public final void apply(RecordComponentVisitor recordComponentVisitor, RecordComponentDescription recordComponentDescription, AnnotationValueFilter annotationValueFilter) {
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/attribute/RecordComponentAttributeAppender$Factory.class */
    public interface Factory {
        RecordComponentAttributeAppender make(TypeDescription typeDescription);

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/attribute/RecordComponentAttributeAppender$Factory$Compound.class */
        public static class Compound implements Factory {
            private final List<Factory> factories;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.factories.equals(((Compound) obj).factories);
            }

            public int hashCode() {
                return (getClass().hashCode() * 31) + this.factories.hashCode();
            }

            public Compound(Factory... factoryArr) {
                this((List<? extends Factory>) Arrays.asList(factoryArr));
            }

            public Compound(List<? extends Factory> list) {
                this.factories = new ArrayList();
                for (Factory factory : list) {
                    if (factory instanceof Compound) {
                        this.factories.addAll(((Compound) factory).factories);
                    } else if (!(factory instanceof NoOp)) {
                        this.factories.add(factory);
                    }
                }
            }

            @Override // net.bytebuddy.implementation.attribute.RecordComponentAttributeAppender.Factory
            public RecordComponentAttributeAppender make(TypeDescription typeDescription) {
                ArrayList arrayList = new ArrayList(this.factories.size());
                Iterator<Factory> it = this.factories.iterator();
                while (it.hasNext()) {
                    arrayList.add(it.next().make(typeDescription));
                }
                return new Compound(arrayList);
            }
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/attribute/RecordComponentAttributeAppender$ForInstrumentedRecordComponent.class */
    public enum ForInstrumentedRecordComponent implements RecordComponentAttributeAppender, Factory {
        INSTANCE;

        @Override // net.bytebuddy.implementation.attribute.RecordComponentAttributeAppender
        public final void apply(RecordComponentVisitor recordComponentVisitor, RecordComponentDescription recordComponentDescription, AnnotationValueFilter annotationValueFilter) {
            AnnotationAppender annotationAppender = (AnnotationAppender) recordComponentDescription.getType().accept(AnnotationAppender.ForTypeAnnotations.ofFieldType(new AnnotationAppender.Default(new AnnotationAppender.Target.OnRecordComponent(recordComponentVisitor)), annotationValueFilter));
            Iterator it = recordComponentDescription.getDeclaredAnnotations().iterator();
            while (it.hasNext()) {
                annotationAppender = annotationAppender.append((AnnotationDescription) it.next(), annotationValueFilter);
            }
        }

        @Override // net.bytebuddy.implementation.attribute.RecordComponentAttributeAppender.Factory
        public final RecordComponentAttributeAppender make(TypeDescription typeDescription) {
            return this;
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/attribute/RecordComponentAttributeAppender$Explicit.class */
    public static class Explicit implements RecordComponentAttributeAppender, Factory {
        private final List<? extends AnnotationDescription> annotations;

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.annotations.equals(((Explicit) obj).annotations);
        }

        public int hashCode() {
            return (getClass().hashCode() * 31) + this.annotations.hashCode();
        }

        public Explicit(List<? extends AnnotationDescription> list) {
            this.annotations = list;
        }

        @Override // net.bytebuddy.implementation.attribute.RecordComponentAttributeAppender
        public void apply(RecordComponentVisitor recordComponentVisitor, RecordComponentDescription recordComponentDescription, AnnotationValueFilter annotationValueFilter) {
            AnnotationAppender.Default r7 = new AnnotationAppender.Default(new AnnotationAppender.Target.OnRecordComponent(recordComponentVisitor));
            Iterator<? extends AnnotationDescription> it = this.annotations.iterator();
            while (it.hasNext()) {
                r7 = r7.append(it.next(), annotationValueFilter);
            }
        }

        @Override // net.bytebuddy.implementation.attribute.RecordComponentAttributeAppender.Factory
        public RecordComponentAttributeAppender make(TypeDescription typeDescription) {
            return this;
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/attribute/RecordComponentAttributeAppender$Compound.class */
    public static class Compound implements RecordComponentAttributeAppender {
        private final List<RecordComponentAttributeAppender> recordComponentAttributeAppenders;

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.recordComponentAttributeAppenders.equals(((Compound) obj).recordComponentAttributeAppenders);
        }

        public int hashCode() {
            return (getClass().hashCode() * 31) + this.recordComponentAttributeAppenders.hashCode();
        }

        public Compound(RecordComponentAttributeAppender... recordComponentAttributeAppenderArr) {
            this((List<? extends RecordComponentAttributeAppender>) Arrays.asList(recordComponentAttributeAppenderArr));
        }

        public Compound(List<? extends RecordComponentAttributeAppender> list) {
            this.recordComponentAttributeAppenders = new ArrayList();
            for (RecordComponentAttributeAppender recordComponentAttributeAppender : list) {
                if (recordComponentAttributeAppender instanceof Compound) {
                    this.recordComponentAttributeAppenders.addAll(((Compound) recordComponentAttributeAppender).recordComponentAttributeAppenders);
                } else if (!(recordComponentAttributeAppender instanceof NoOp)) {
                    this.recordComponentAttributeAppenders.add(recordComponentAttributeAppender);
                }
            }
        }

        @Override // net.bytebuddy.implementation.attribute.RecordComponentAttributeAppender
        public void apply(RecordComponentVisitor recordComponentVisitor, RecordComponentDescription recordComponentDescription, AnnotationValueFilter annotationValueFilter) {
            Iterator<RecordComponentAttributeAppender> it = this.recordComponentAttributeAppenders.iterator();
            while (it.hasNext()) {
                it.next().apply(recordComponentVisitor, recordComponentDescription, annotationValueFilter);
            }
        }
    }
}
