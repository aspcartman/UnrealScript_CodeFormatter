package ru.mipt.LineFormatters;

import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.mipt.Source;
import ru.mipt.SourceFormatter;

/**
 * MIPT
 * Autor: aspcartman
 * Date: 04.09.13
 */
public class OperatorAlignmentTest
{
	SourceFormatter formatter;

	@Before
	public void setUp() throws Exception
	{
		formatter = new SourceFormatter(new OperatorAlignmentFormatter());
	}

	@After
	public void tearDown() throws Exception
	{
		formatter = null;
	}

	@Test
	public void testOperatorAlignment() throws Exception
	{
		Source input = new Source();
		input.addLine("");
		input.addLine("someVar = lol;");
		input.addLine("someOtherVar = lol;");
		input.addLine("");
		input.addLine("someVar = lol;");
		input.addLine("s = t");
		input.addLine("smth=nothing");
		input.addLine("");

		Source actual = formatter.Format(input);

		Source expected = new Source();
		expected.addLine("");
		expected.addLine("someVar      = lol;");
		expected.addLine("someOtherVar = lol;");
		expected.addLine("");
		expected.addLine("someVar = lol;");
		expected.addLine("s       = t");
		expected.addLine("smth    =nothing");
		expected.addLine("");

		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testOperatorAlignment2() throws Exception
	{
		Source input = new Source();
		input.addLine("");
		input.addLine("Begin Object Class=CylinderComponent Name=CollisionCylinder");
		input.addLine("CollisionRadius=+0034.000000");
		input.addLine("CollisionHeight=+0078.000000");
		input.addLine("BlockNonZeroExtent=true");
		input.addLine("BlockZeroExtent=true");
		input.addLine("BlockActors=true");
		input.addLine("CollideActors=true");
		input.addLine("End Object");
		input.addLine("");

		Source actual = formatter.Format(input);

		Source expected = new Source();
		expected.addLine("");
		expected.addLine("Begin Object Class=CylinderComponent Name=CollisionCylinder");
		expected.addLine("CollisionRadius   =+0034.000000");
		expected.addLine("CollisionHeight   =+0078.000000");
		expected.addLine("BlockNonZeroExtent=true");
		expected.addLine("BlockZeroExtent   =true");
		expected.addLine("BlockActors       =true");
		expected.addLine("CollideActors     =true");
		expected.addLine("End Object");
		expected.addLine("");

		Assert.assertEquals(expected, actual);
	}
}
