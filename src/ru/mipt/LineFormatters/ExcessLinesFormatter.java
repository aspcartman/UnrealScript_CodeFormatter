package ru.mipt.LineFormatters;

import ru.mipt.InvalidSyntaxException;
import ru.mipt.LineFormatter;
import ru.mipt.Source;

/**
 * Deletes excess empty lines.
 */
public class ExcessLinesFormatter implements LineFormatter
{
	boolean previousLineWasEmpty = false;

	@Override
	public boolean Condition(Source source, int lineNumber)
	{
		String line = source.getLine(lineNumber);

		if (! line.isEmpty())
		{
			previousLineWasEmpty = false;
			return false;
		}

		if (previousLineWasEmpty)
		{
			return true;
		}

		previousLineWasEmpty = true;
		return false;
	}

	@Override
	public void Format(Source source, int lineNumber)
	{
		source.removeLine(lineNumber);
	}

	@Override
	public boolean NeedsRerun()
	{
		return true;
	}

	@Override
	public void Finished() throws InvalidSyntaxException
	{
		/* Always Ok */
	}
}
