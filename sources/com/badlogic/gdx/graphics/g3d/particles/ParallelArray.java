package com.badlogic.gdx.graphics.g3d.particles;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.reflect.ArrayReflection;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/particles/ParallelArray.class */
public class ParallelArray {
    public int capacity;
    Array<Channel> arrays = new Array<>(false, 2, Channel.class);
    public int size = 0;

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/particles/ParallelArray$ChannelInitializer.class */
    public interface ChannelInitializer<T extends Channel> {
        void init(T t);
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/particles/ParallelArray$ChannelDescriptor.class */
    public static class ChannelDescriptor {
        public int id;
        public Class<?> type;
        public int count;

        public ChannelDescriptor(int i, Class<?> cls, int i2) {
            this.id = i;
            this.type = cls;
            this.count = i2;
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/particles/ParallelArray$Channel.class */
    public abstract class Channel {
        public int id;
        public Object data;
        public int strideSize;

        public abstract void add(int i, Object... objArr);

        public abstract void swap(int i, int i2);

        protected abstract void setCapacity(int i);

        public Channel(int i, Object obj, int i2) {
            this.id = i;
            this.strideSize = i2;
            this.data = obj;
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/particles/ParallelArray$FloatChannel.class */
    public class FloatChannel extends Channel {
        public float[] data;

        public FloatChannel(int i, int i2, int i3) {
            super(i, new float[i3 * i2], i2);
            this.data = (float[]) super.data;
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParallelArray.Channel
        public void add(int i, Object... objArr) {
            int i2 = this.strideSize * ParallelArray.this.size;
            int i3 = i2;
            int i4 = i2 + this.strideSize;
            int i5 = 0;
            while (i3 < i4) {
                this.data[i3] = ((Float) objArr[i5]).floatValue();
                i3++;
                i5++;
            }
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParallelArray.Channel
        public void swap(int i, int i2) {
            int i3 = this.strideSize * i;
            int i4 = this.strideSize * i2;
            int i5 = i3 + this.strideSize;
            while (i3 < i5) {
                float f = this.data[i3];
                float[] fArr = this.data;
                fArr[i3] = fArr[i4];
                this.data[i4] = f;
                i3++;
                i4++;
            }
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParallelArray.Channel
        public void setCapacity(int i) {
            float[] fArr = new float[this.strideSize * i];
            System.arraycopy(this.data, 0, fArr, 0, Math.min(this.data.length, fArr.length));
            this.data = fArr;
            super.data = fArr;
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/particles/ParallelArray$IntChannel.class */
    public class IntChannel extends Channel {
        public int[] data;

        public IntChannel(int i, int i2, int i3) {
            super(i, new int[i3 * i2], i2);
            this.data = (int[]) super.data;
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParallelArray.Channel
        public void add(int i, Object... objArr) {
            int i2 = this.strideSize * ParallelArray.this.size;
            int i3 = i2;
            int i4 = i2 + this.strideSize;
            int i5 = 0;
            while (i3 < i4) {
                this.data[i3] = ((Integer) objArr[i5]).intValue();
                i3++;
                i5++;
            }
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParallelArray.Channel
        public void swap(int i, int i2) {
            int i3 = this.strideSize * i;
            int i4 = this.strideSize * i2;
            int i5 = i3 + this.strideSize;
            while (i3 < i5) {
                int i6 = this.data[i3];
                int[] iArr = this.data;
                iArr[i3] = iArr[i4];
                this.data[i4] = i6;
                i3++;
                i4++;
            }
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParallelArray.Channel
        public void setCapacity(int i) {
            int[] iArr = new int[this.strideSize * i];
            System.arraycopy(this.data, 0, iArr, 0, Math.min(this.data.length, iArr.length));
            this.data = iArr;
            super.data = iArr;
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/particles/ParallelArray$ObjectChannel.class */
    public class ObjectChannel<T> extends Channel {
        Class<T> componentType;
        public T[] data;

        public ObjectChannel(int i, int i2, int i3, Class<T> cls) {
            super(i, ArrayReflection.newInstance(cls, i3 * i2), i2);
            this.componentType = cls;
            this.data = (T[]) ((Object[]) super.data);
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParallelArray.Channel
        public void add(int i, Object... objArr) {
            int i2 = this.strideSize * ParallelArray.this.size;
            int i3 = i2;
            int i4 = i2 + this.strideSize;
            int i5 = 0;
            while (i3 < i4) {
                ((T[]) this.data)[i3] = objArr[i5];
                i3++;
                i5++;
            }
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParallelArray.Channel
        public void swap(int i, int i2) {
            int i3 = this.strideSize * i;
            int i4 = this.strideSize * i2;
            int i5 = i3 + this.strideSize;
            while (i3 < i5) {
                T t = this.data[i3];
                T[] tArr = this.data;
                tArr[i3] = tArr[i4];
                this.data[i4] = t;
                i3++;
                i4++;
            }
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParallelArray.Channel
        public void setCapacity(int i) {
            T[] tArr = (T[]) ((Object[]) ArrayReflection.newInstance(this.componentType, this.strideSize * i));
            System.arraycopy(this.data, 0, tArr, 0, Math.min(this.data.length, tArr.length));
            this.data = tArr;
            super.data = tArr;
        }
    }

    public ParallelArray(int i) {
        this.capacity = i;
    }

    public <T extends Channel> T addChannel(ChannelDescriptor channelDescriptor) {
        return (T) addChannel(channelDescriptor, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [com.badlogic.gdx.graphics.g3d.particles.ParallelArray$Channel] */
    /* JADX WARN: Type inference failed for: r0v4, types: [com.badlogic.gdx.graphics.g3d.particles.ParallelArray$Channel] */
    public <T extends Channel> T addChannel(ChannelDescriptor channelDescriptor, ChannelInitializer<T> channelInitializer) {
        ?? channel = getChannel(channelDescriptor);
        T t = channel;
        if (channel == 0) {
            t = allocateChannel(channelDescriptor);
            if (channelInitializer != null) {
                channelInitializer.init(t);
            }
            this.arrays.add(t);
        }
        return t;
    }

    private <T extends Channel> T allocateChannel(ChannelDescriptor channelDescriptor) {
        if (channelDescriptor.type == Float.TYPE) {
            return new FloatChannel(channelDescriptor.id, channelDescriptor.count, this.capacity);
        }
        if (channelDescriptor.type == Integer.TYPE) {
            return new IntChannel(channelDescriptor.id, channelDescriptor.count, this.capacity);
        }
        return new ObjectChannel(channelDescriptor.id, channelDescriptor.count, this.capacity, channelDescriptor.type);
    }

    public <T> void removeArray(int i) {
        this.arrays.removeIndex(findIndex(i));
    }

    private int findIndex(int i) {
        for (int i2 = 0; i2 < this.arrays.size; i2++) {
            if (this.arrays.items[i2].id == i) {
                return i2;
            }
        }
        return -1;
    }

    public void addElement(Object... objArr) {
        if (this.size == this.capacity) {
            throw new GdxRuntimeException("Capacity reached, cannot add other elements");
        }
        int i = 0;
        Array.ArrayIterator<Channel> it = this.arrays.iterator();
        while (it.hasNext()) {
            Channel next = it.next();
            next.add(i, objArr);
            i += next.strideSize;
        }
        this.size++;
    }

    public void removeElement(int i) {
        int i2 = this.size - 1;
        Array.ArrayIterator<Channel> it = this.arrays.iterator();
        while (it.hasNext()) {
            it.next().swap(i, i2);
        }
        this.size = i2;
    }

    public <T extends Channel> T getChannel(ChannelDescriptor channelDescriptor) {
        Array.ArrayIterator<Channel> it = this.arrays.iterator();
        while (it.hasNext()) {
            T t = (T) it.next();
            if (t.id == channelDescriptor.id) {
                return t;
            }
        }
        return null;
    }

    public void clear() {
        this.arrays.clear();
        this.size = 0;
    }

    public void setCapacity(int i) {
        if (this.capacity != i) {
            Array.ArrayIterator<Channel> it = this.arrays.iterator();
            while (it.hasNext()) {
                it.next().setCapacity(i);
            }
            this.capacity = i;
        }
    }
}
