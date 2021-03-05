package com.github.groomon.gslib.locations;

import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public class ImmutableVector extends Vector {

    /**
     * Creates an {@link ImmutableVector} based on the given {@link Vector}.
     *
     * @param vector The old Vector
     */
    public ImmutableVector(Vector vector) {
        this.x = vector.getX();
        this.y = vector.getY();
        this.z = vector.getZ();
    }

    /**
     * Adds a vector to this one
     *
     * @param vec The other vector
     * @return the same vector
     */
    @NotNull
    @Override
    public Vector add(@NotNull Vector vec) {
        throw new UnsupportedOperationException("Cannot modify immutable Vector");
    }

    /**
     * Subtracts a vector from this one.
     *
     * @param vec The other vector
     * @return the same vector
     */
    @NotNull
    @Override
    public Vector subtract(@NotNull Vector vec) {
        throw new UnsupportedOperationException("Cannot modify immutable Vector");
    }

    /**
     * Multiplies the vector by another.
     *
     * @param vec The other vector
     * @return the same vector
     */
    @NotNull
    @Override
    public Vector multiply(@NotNull Vector vec) {
        throw new UnsupportedOperationException("Cannot modify immutable Vector");
    }

    /**
     * Divides the vector by another.
     *
     * @param vec The other vector
     * @return the same vector
     */
    @NotNull
    @Override
    public Vector divide(@NotNull Vector vec) {
        throw new UnsupportedOperationException("Cannot modify immutable Vector");
    }

    /**
     * Performs scalar multiplication, multiplying all components with a
     * scalar.
     *
     * @param m The factor
     * @return the same vector
     */
    @NotNull
    @Override
    public Vector multiply(int m) {
        throw new UnsupportedOperationException("Cannot modify immutable Vector");
    }

    /**
     * Performs scalar multiplication, multiplying all components with a
     * scalar.
     *
     * @param m The factor
     * @return the same vector
     */
    @NotNull
    @Override
    public Vector multiply(double m) {
        throw new UnsupportedOperationException("Cannot modify immutable Vector");
    }

    /**
     * Performs scalar multiplication, multiplying all components with a
     * scalar.
     *
     * @param m The factor
     * @return the same vector
     */
    @NotNull
    @Override
    public Vector multiply(float m) {
        throw new UnsupportedOperationException("Cannot modify immutable Vector");
    }

    /**
     * Calculates the cross product of this vector with another. The cross
     * product is defined as:
     * <ul>
     * <li>x = y1 * z2 - y2 * z1
     * <li>y = z1 * x2 - z2 * x1
     * <li>z = x1 * y2 - x2 * y1
     * </ul>
     *
     * @param o The other vector
     * @return the same vector
     */
    @NotNull
    @Override
    public Vector crossProduct(@NotNull Vector o) {
        throw new UnsupportedOperationException("Cannot modify immutable Vector");
    }

    /**
     * Converts this vector to a unit vector (a vector with length of 1).
     *
     * @return the same vector
     */
    @NotNull
    @Override
    public Vector normalize() {
        throw new UnsupportedOperationException("Cannot modify immutable Vector");
    }

    /**
     * Zero this vector's components.
     *
     * @return the same vector
     */
    @NotNull
    @Override
    public Vector zero() {
        throw new UnsupportedOperationException("Cannot modify immutable Vector");
    }

    /**
     * Rotates the vector around the x axis.
     * <p>
     * This piece of math is based on the standard rotation matrix for vectors
     * in three dimensional space. This matrix can be found here:
     * <a href="https://en.wikipedia.org/wiki/Rotation_matrix#Basic_rotations">Rotation
     * Matrix</a>.
     *
     * @param angle the angle to rotate the vector about. This angle is passed
     *              in radians
     * @return the same vector
     */
    @NotNull
    @Override
    public Vector rotateAroundX(double angle) {
        throw new UnsupportedOperationException("Cannot modify immutable Vector");
    }

    /**
     * Rotates the vector around the y axis.
     * <p>
     * This piece of math is based on the standard rotation matrix for vectors
     * in three dimensional space. This matrix can be found here:
     * <a href="https://en.wikipedia.org/wiki/Rotation_matrix#Basic_rotations">Rotation
     * Matrix</a>.
     *
     * @param angle the angle to rotate the vector about. This angle is passed
     *              in radians
     * @return the same vector
     */
    @NotNull
    @Override
    public Vector rotateAroundY(double angle) {
        throw new UnsupportedOperationException("Cannot modify immutable Vector");
    }

    /**
     * Rotates the vector around the z axis
     * <p>
     * This piece of math is based on the standard rotation matrix for vectors
     * in three dimensional space. This matrix can be found here:
     * <a href="https://en.wikipedia.org/wiki/Rotation_matrix#Basic_rotations">Rotation
     * Matrix</a>.
     *
     * @param angle the angle to rotate the vector about. This angle is passed
     *              in radians
     * @return the same vector
     */
    @NotNull
    @Override
    public Vector rotateAroundZ(double angle) {
        throw new UnsupportedOperationException("Cannot modify immutable Vector");
    }

    /**
     * Rotates the vector around a given arbitrary axis in 3 dimensional space.
     *
     * <p>
     * Rotation will follow the general Right-Hand-Rule, which means rotation
     * will be counterclockwise when the axis is pointing towards the observer.
     * <p>
     * This method will always make sure the provided axis is a unit vector, to
     * not modify the length of the vector when rotating. If you are experienced
     * with the scaling of a non-unit axis vector, you can use
     * {@link Vector#rotateAroundNonUnitAxis(Vector, double)}.
     *
     * @param axis  the axis to rotate the vector around. If the passed vector is
     *              not of length 1, it gets copied and normalized before using it for the
     *              rotation. Please use {@link Vector#normalize()} on the instance before
     *              passing it to this method
     * @param angle the angle to rotate the vector around the axis
     * @return the same vector
     * @throws IllegalArgumentException if the provided axis vector instance is
     *                                  null
     */
    @NotNull
    @Override
    public Vector rotateAroundAxis(@NotNull Vector axis, double angle) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Cannot modify immutable Vector");
    }

    /**
     * Rotates the vector around a given arbitrary axis in 3 dimensional space.
     *
     * <p>
     * Rotation will follow the general Right-Hand-Rule, which means rotation
     * will be counterclockwise when the axis is pointing towards the observer.
     * <p>
     * Note that the vector length will change accordingly to the axis vector
     * length. If the provided axis is not a unit vector, the rotated vector
     * will not have its previous length. The scaled length of the resulting
     * vector will be related to the axis vector. If you are not perfectly sure
     * about the scaling of the vector, use
     * {@link Vector#rotateAroundAxis(Vector, double)}
     *
     * @param axis  the axis to rotate the vector around.
     * @param angle the angle to rotate the vector around the axis
     * @return the same vector
     * @throws IllegalArgumentException if the provided axis vector instance is
     *                                  null
     */
    @NotNull
    @Override
    public Vector rotateAroundNonUnitAxis(@NotNull Vector axis, double angle) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Cannot modify immutable Vector");
    }

    /**
     * Set the X component.
     *
     * @param x The new X component.
     * @return This vector.
     */
    @NotNull
    @Override
    public Vector setX(int x) {
        throw new UnsupportedOperationException("Cannot modify immutable Vector");
    }

    /**
     * Set the X component.
     *
     * @param x The new X component.
     * @return This vector.
     */
    @NotNull
    @Override
    public Vector setX(double x) {
        throw new UnsupportedOperationException("Cannot modify immutable Vector");
    }

    /**
     * Set the X component.
     *
     * @param x The new X component.
     * @return This vector.
     */
    @NotNull
    @Override
    public Vector setX(float x) {
        throw new UnsupportedOperationException("Cannot modify immutable Vector");
    }

    /**
     * Set the Y component.
     *
     * @param y The new Y component.
     * @return This vector.
     */
    @NotNull
    @Override
    public Vector setY(int y) {
        throw new UnsupportedOperationException("Cannot modify immutable Vector");
    }

    /**
     * Set the Y component.
     *
     * @param y The new Y component.
     * @return This vector.
     */
    @NotNull
    @Override
    public Vector setY(double y) {
        throw new UnsupportedOperationException("Cannot modify immutable Vector");
    }

    /**
     * Set the Y component.
     *
     * @param y The new Y component.
     * @return This vector.
     */
    @NotNull
    @Override
    public Vector setY(float y) {
        throw new UnsupportedOperationException("Cannot modify immutable Vector");
    }

    /**
     * Set the Z component.
     *
     * @param z The new Z component.
     * @return This vector.
     */
    @NotNull
    @Override
    public Vector setZ(int z) {
        throw new UnsupportedOperationException("Cannot modify immutable Vector");
    }

    /**
     * Set the Z component.
     *
     * @param z The new Z component.
     * @return This vector.
     */
    @NotNull
    @Override
    public Vector setZ(double z) {
        throw new UnsupportedOperationException("Cannot modify immutable Vector");
    }

    /**
     * Set the Z component.
     *
     * @param z The new Z component.
     * @return This vector.
     */
    @NotNull
    @Override
    public Vector setZ(float z) {
        throw new UnsupportedOperationException("Cannot modify immutable Vector");
    }

    /**
     * Copies another vector
     *
     * @param vec The other vector
     * @return the same vector
     */
    @NotNull
    @Override
    public Vector copy(@NotNull Vector vec) {
        throw new UnsupportedOperationException("Cannot modify immutable Vector");
    }
}
