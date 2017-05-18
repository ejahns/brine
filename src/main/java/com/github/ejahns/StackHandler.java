package com.github.ejahns;

/**
 * A wrapper for a stack; capable of taking input from a parser
 * and generating a representation of the document being parsed.
 *
 * @param <S> class of stack elements
 * @param <T> class of elements consumable by S
 */
public interface StackHandler<S, T> {

	/**
	 * Push a new instance of the specified class onto the top of the stack.
	 *
	 * @param clazz class of object to be pushed
	 */
	void push(Class<? extends S> clazz);

	/**
	 * Pop the top element off the stack, and supply to the new top element for consumption
	 *
	 * @param clazz class of object to be popped (supplied as a sanity check and to make
	 *              calls to this method human-readable).
	 */
	void popConsume(Class<? extends S> clazz);

	/**
	 * Resolve the stack to the bottom-most element by popping the top element
	 * and supplying to the new top element for consumption repeatedly until
	 * the bottom of the stack is reached.
	 *
	 * @return the bottom-most element of the stack, having consumed all elements above
	 */
	S resolve();

	/**
	 * Supply an object of type T to the top stack element for consumption.
	 *
	 * @param t object to be consumed by the top stack element
	 */
	void consume(T t);
}
