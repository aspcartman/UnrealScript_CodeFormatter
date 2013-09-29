package ru.mipt.LineFormatters;

import ru.mipt.InvalidSyntaxException;
import ru.mipt.LineFormatter;
import ru.mipt.Source;

/**
 * MIPT
 * Autor: aspcartman
 * Date: 29.09.13
 */
public class LabelFormatter implements LineFormatter
{

	@Override
	public boolean Condition(Source source, int lineNumber)
	{
		return source.getLine(lineNumber).matches("\t.*:");
	}

	@Override
	public void Format(Source source, int lineNumber)
	{
		String line = source.getLine(lineNumber);
		line = line.replaceFirst("\t","");
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
	}
}
