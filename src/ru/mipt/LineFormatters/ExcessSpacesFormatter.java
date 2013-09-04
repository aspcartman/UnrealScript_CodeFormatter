package ru.mipt.LineFormatters;

import ru.mipt.InvalidSyntaxException;
import ru.mipt.LineFormatter;
import ru.mipt.Source;

/**
 * Removes excess spaces.
 */
public class ExcessSpacesFormatter implements LineFormatter
{
	@Override
	public boolean Condition(Source source, int lineNumber)
	{
		return true;
	}

	@Override
	public void Format(Source source, int lineNumber)
	{
		String line = source.getLine(lineNumber);
		line = line.replaceAll("\\s+", " ");
		source.setLine(lineNumber,line);
	}

	@Override
	public boolean NeedsRerun()
	{
		return false;
	}

	@Override
	public void Finished() throws InvalidSyntaxException
	{
		/* Always Ok */
	}
}
