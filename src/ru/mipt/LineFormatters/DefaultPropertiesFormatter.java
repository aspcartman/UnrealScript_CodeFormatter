package ru.mipt.LineFormatters;

import ru.mipt.InvalidSyntaxException;
import ru.mipt.LineFormatter;
import ru.mipt.Source;

/**
 * MIPT
 * Autor: aspcartman
 * Date: 04.09.13
 */
public class DefaultPropertiesFormatter implements LineFormatter
{
	@Override
	public boolean Condition(Source source, int lineNumber)
	{
		String line = source.getLine(lineNumber);
		return line.toLowerCase().equals("defaultproperties");
	}

	@Override
	public void Format(Source source, int lineNumber)
	{
		source.setLine(lineNumber,"DefaultProperties");
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
