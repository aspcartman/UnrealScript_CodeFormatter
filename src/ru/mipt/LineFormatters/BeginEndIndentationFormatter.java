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
		indentationLevel += line.contains("Begin ") ? 1 : 0;
		indentationLevel += line.contains("begin ") ? 1 : 0;
	}

	@Override
	protected void DecreaseIndentationIfNeeded(String line)
	{
		indentationLevel -= line.contains("End ") ? 1 : 0;
		indentationLevel -= line.contains("end ") ? 1 : 0;
	}
}
