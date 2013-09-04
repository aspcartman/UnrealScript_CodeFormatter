package ru.mipt.LineFormatters;

import ru.mipt.InvalidSyntaxException;
import ru.mipt.LineFormatter;
import ru.mipt.Source;

/**
 * Trimes a line, removing spaces and tabs from it's start and end.
 */
public class TrimFormatter implements LineFormatter
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
		line = line.trim();
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
