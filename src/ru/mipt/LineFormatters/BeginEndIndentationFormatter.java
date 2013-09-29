package ru.mipt.LineFormatters;

/**
 * Formatter responsible for indentation is DefaultProperties block.
 * Note spaces after begin\end!
 */
public class BeginEndIndentationFormatter extends IndentationFormatter
{
	@Override
	protected void IncreaseIndentationIfNeeded(String line)
	{
		indentationLevel += line.matches("(?i:.*Begin Object.*)") ? 1 : 0;
	}

	@Override
	protected void DecreaseIndentationIfNeeded(String line)
	{
		indentationLevel -= line.matches("(?i:.*End Object.*)") ? 1 : 0;
	}
}
