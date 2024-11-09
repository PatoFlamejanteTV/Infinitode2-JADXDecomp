package com.badlogic.gdx.ai.steer.behaviors;

import com.badlogic.gdx.ai.GdxAI;
import com.badlogic.gdx.ai.steer.Limiter;
import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.SteerableAdapter;
import com.badlogic.gdx.ai.steer.SteeringAcceleration;
import com.badlogic.gdx.math.Vector;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/steer/behaviors/Jump.class */
public class Jump<T extends Vector<T>> extends MatchVelocity<T> {
    public static boolean DEBUG_ENABLED = false;
    protected JumpDescriptor<T> jumpDescriptor;
    protected T gravity;
    protected GravityComponentHandler<T> gravityComponentHandler;
    protected JumpCallback callback;
    protected float takeoffPositionTolerance;
    protected float takeoffVelocityTolerance;
    protected float maxVerticalVelocity;
    private boolean isJumpAchievable;
    protected float airborneTime;
    private JumpTarget<T> jumpTarget;
    private T planarVelocity;

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/steer/behaviors/Jump$GravityComponentHandler.class */
    public interface GravityComponentHandler<T extends Vector<T>> {
        float getComponent(T t);

        void setComponent(T t, float f);
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/steer/behaviors/Jump$JumpCallback.class */
    public interface JumpCallback {
        void reportAchievability(boolean z);

        void takeoff(float f, float f2);
    }

    public Jump(Steerable<T> steerable, JumpDescriptor<T> jumpDescriptor, T t, GravityComponentHandler<T> gravityComponentHandler, JumpCallback jumpCallback) {
        super(steerable);
        this.airborneTime = 0.0f;
        this.gravity = t;
        this.gravityComponentHandler = gravityComponentHandler;
        setJumpDescriptor(jumpDescriptor);
        this.callback = jumpCallback;
        this.jumpTarget = new JumpTarget<>(steerable);
        this.planarVelocity = newVector(steerable);
    }

    @Override // com.badlogic.gdx.ai.steer.behaviors.MatchVelocity, com.badlogic.gdx.ai.steer.SteeringBehavior
    public SteeringAcceleration<T> calculateRealSteering(SteeringAcceleration<T> steeringAcceleration) {
        if (this.target == null) {
            this.target = calculateTarget();
            this.callback.reportAchievability(this.isJumpAchievable);
        }
        if (!this.isJumpAchievable) {
            return steeringAcceleration.setZero();
        }
        if (this.owner.getPosition().epsilonEquals(this.target.getPosition(), this.takeoffPositionTolerance)) {
            if (DEBUG_ENABLED) {
                GdxAI.getLogger().info("Jump", "Good position!!!");
            }
            if (this.owner.getLinearVelocity().epsilonEquals(this.target.getLinearVelocity(), this.takeoffVelocityTolerance)) {
                if (DEBUG_ENABLED) {
                    GdxAI.getLogger().info("Jump", "Good Velocity!!!");
                }
                this.isJumpAchievable = false;
                this.callback.takeoff(this.maxVerticalVelocity, this.airborneTime);
                return steeringAcceleration.setZero();
            }
            if (DEBUG_ENABLED) {
                GdxAI.getLogger().info("Jump", "Bad Velocity: Speed diff. = " + this.planarVelocity.set(this.target.getLinearVelocity()).sub(this.owner.getLinearVelocity()).len() + ", diff = (" + this.planarVelocity + ")");
            }
        }
        return super.calculateRealSteering(steeringAcceleration);
    }

    private Steerable<T> calculateTarget() {
        this.jumpTarget.position = this.jumpDescriptor.takeoffPosition;
        this.airborneTime = calculateAirborneTimeAndVelocity(this.jumpTarget.linearVelocity, this.jumpDescriptor, getActualLimiter().getMaxLinearSpeed());
        this.isJumpAchievable = this.airborneTime >= 0.0f;
        return this.jumpTarget;
    }

    public float calculateAirborneTimeAndVelocity(T t, JumpDescriptor<T> jumpDescriptor, float f) {
        float component = this.gravityComponentHandler.getComponent(this.gravity);
        float sqrt = (float) Math.sqrt((component * 2.0f * this.gravityComponentHandler.getComponent(jumpDescriptor.delta)) + (this.maxVerticalVelocity * this.maxVerticalVelocity));
        float f2 = ((-this.maxVerticalVelocity) + sqrt) / component;
        if (DEBUG_ENABLED) {
            GdxAI.getLogger().info("Jump", "1st jump time = " + f2);
        }
        if (!checkAirborneTimeAndCalculateVelocity(t, f2, jumpDescriptor, f)) {
            f2 = ((-this.maxVerticalVelocity) - sqrt) / component;
            if (DEBUG_ENABLED) {
                GdxAI.getLogger().info("Jump", "2nd jump time = " + f2);
            }
            if (!checkAirborneTimeAndCalculateVelocity(t, f2, jumpDescriptor, f)) {
                return -1.0f;
            }
        }
        return f2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private boolean checkAirborneTimeAndCalculateVelocity(T t, float f, JumpDescriptor<T> jumpDescriptor, float f2) {
        this.planarVelocity.set(jumpDescriptor.delta).scl(1.0f / f);
        this.gravityComponentHandler.setComponent(this.planarVelocity, 0.0f);
        if (this.planarVelocity.len2() < f2 * f2) {
            this.gravityComponentHandler.setComponent(t.set(this.planarVelocity), this.gravityComponentHandler.getComponent(t));
            if (DEBUG_ENABLED) {
                GdxAI.getLogger().info("Jump", "targetLinearVelocity = " + t + "; targetLinearSpeed = " + t.len());
                return true;
            }
            return true;
        }
        return false;
    }

    public JumpDescriptor<T> getJumpDescriptor() {
        return this.jumpDescriptor;
    }

    public Jump<T> setJumpDescriptor(JumpDescriptor<T> jumpDescriptor) {
        this.jumpDescriptor = jumpDescriptor;
        this.target = null;
        this.isJumpAchievable = false;
        return this;
    }

    public T getGravity() {
        return this.gravity;
    }

    public Jump<T> setGravity(T t) {
        this.gravity = t;
        return this;
    }

    public float getMaxVerticalVelocity() {
        return this.maxVerticalVelocity;
    }

    public Jump<T> setMaxVerticalVelocity(float f) {
        this.maxVerticalVelocity = f;
        return this;
    }

    public float getTakeoffPositionTolerance() {
        return this.takeoffPositionTolerance;
    }

    public Jump<T> setTakeoffPositionTolerance(float f) {
        this.takeoffPositionTolerance = f;
        return this;
    }

    public float getTakeoffVelocityTolerance() {
        return this.takeoffVelocityTolerance;
    }

    public Jump<T> setTakeoffVelocityTolerance(float f) {
        this.takeoffVelocityTolerance = f;
        return this;
    }

    public Jump<T> setTakeoffTolerance(float f) {
        setTakeoffPositionTolerance(f);
        setTakeoffVelocityTolerance(f);
        return this;
    }

    @Override // com.badlogic.gdx.ai.steer.behaviors.MatchVelocity, com.badlogic.gdx.ai.steer.SteeringBehavior
    public Jump<T> setOwner(Steerable<T> steerable) {
        this.owner = steerable;
        return this;
    }

    @Override // com.badlogic.gdx.ai.steer.behaviors.MatchVelocity, com.badlogic.gdx.ai.steer.SteeringBehavior
    public Jump<T> setEnabled(boolean z) {
        this.enabled = z;
        return this;
    }

    @Override // com.badlogic.gdx.ai.steer.behaviors.MatchVelocity, com.badlogic.gdx.ai.steer.SteeringBehavior
    public Jump<T> setLimiter(Limiter limiter) {
        this.limiter = limiter;
        return this;
    }

    @Override // com.badlogic.gdx.ai.steer.behaviors.MatchVelocity
    public Jump<T> setTarget(Steerable<T> steerable) {
        this.target = steerable;
        return this;
    }

    @Override // com.badlogic.gdx.ai.steer.behaviors.MatchVelocity
    public Jump<T> setTimeToTarget(float f) {
        this.timeToTarget = f;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/steer/behaviors/Jump$JumpTarget.class */
    public static class JumpTarget<T extends Vector<T>> extends SteerableAdapter<T> {
        T position = null;
        T linearVelocity;

        public JumpTarget(Steerable<T> steerable) {
            this.linearVelocity = (T) steerable.getPosition().cpy().setZero();
        }

        @Override // com.badlogic.gdx.ai.steer.SteerableAdapter, com.badlogic.gdx.ai.utils.Location
        public T getPosition() {
            return this.position;
        }

        @Override // com.badlogic.gdx.ai.steer.SteerableAdapter, com.badlogic.gdx.ai.steer.Steerable
        public T getLinearVelocity() {
            return this.linearVelocity;
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/steer/behaviors/Jump$JumpDescriptor.class */
    public static class JumpDescriptor<T extends Vector<T>> {
        public T takeoffPosition;
        public T landingPosition;
        public T delta;

        public JumpDescriptor(T t, T t2) {
            this.takeoffPosition = t;
            this.landingPosition = t2;
            this.delta = (T) t2.cpy();
            set(t, t2);
        }

        public void set(T t, T t2) {
            this.takeoffPosition.set(t);
            this.landingPosition.set(t2);
            this.delta.set(t2).sub(t);
        }
    }
}
