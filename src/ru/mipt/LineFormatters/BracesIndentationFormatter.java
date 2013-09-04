package ru.mipt.LineFormatters;

/**
 * Indentation in code block.
 */
public class BracesIndentationFormatter extends IndentationFormatter
{
	@Override
	protected void IncreaseIndentationIfNeeded(String line)
	{
		indentationLevel += line.replaceAll("[^{]", "").length();
	}

	@Override
	protected void DecreaseIndentationIfNeeded(String line)
	{
		indentationLevel -= line.replaceAll("[^}]", "").length();
	}
}
