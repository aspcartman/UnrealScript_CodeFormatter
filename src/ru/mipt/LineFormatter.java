package ru.mipt;

/**
 * Formatters are used to make manipulations with source by analyzing One line and making
 * changes to one or more.
 *
 * SourceFormatter asks LineFormatter if it wants to make changes to a line by calling
 * Condition() method, and if it does calls Format.
 *
 * After all the manipulations with source SourceFormatter calls the Finish() method
 * on every LineFormatter.
 */
public interface LineFormatter
{
	/**
	 * Check here should you or not alter this line.
	 * @param source
	 * @param lineNumber
	 * @return
	 */
	public abstract boolean Condition(Source source, int lineNumber);

	/**
	 * Make all modification you need. Feel like at home.
	 * @param source
	 * @param lineNumber
	 */
	public abstract void Format(Source source, int lineNumber);

	/**
	 * Tells SourceFormatter to restart the LineFormatters chain for current line.
	 * Usefull if, for example, formatter decided to delete a line. If it did,
	 * SourceFormatter has no way to notice it except that.
	 * @return bool
	 */
	public abstract boolean NeedsRerun();

	/**
	 * Called after all processing is done.
	 * Prepare your formatter for reuse here and, if you think processing shouldn't be
	 * finished yet (for example Indentation level != 0), throw a fireball.
	 * @throws InvalidSyntaxException
	 */
	public abstract void Finished() throws InvalidSyntaxException;
}
