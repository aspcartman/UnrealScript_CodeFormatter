package ru.mipt.LineFormatters;

import ru.mipt.InvalidSyntaxException;
import ru.mipt.LineFormatter;
import ru.mipt.Source;

/**
 * MIPT
 * Autor: aspcartman
 * Date: 04.09.13
 */
public abstract class IndentationFormatter implements LineFormatter
{
	protected int indentationLevel = 0;

	@Override
	public boolean Condition(Source source, int lineNumber)
	{
		return true;
	}

	@Override
	public void Format(Source source, int lineNumber)
	{
		String line = source.getLine(lineNumber);

		DecreaseIndentationIfNeeded(line);
		for (int i = 0; i < indentationLevel; ++ i)
		{
			line = "\t" + line;
		}
		IncreaseIndentationIfNeeded(line);

		if (indentationLevel < 0)
		{
			throw new InvalidSyntaxException("Indentation failure");
		}

		source.setLine(lineNumber, line);
	}

	protected abstract void IncreaseIndentationIfNeeded(String line);

	protected abstract void DecreaseIndentationIfNeeded(String line);

	@Override
	public boolean NeedsRerun()
	{
		return false;
	}

	@Override
	public void Finished() throws InvalidSyntaxException
	{
		if (indentationLevel != 0)
		{
			throw new InvalidSyntaxException("Indentation failure");
		}
	}
}
