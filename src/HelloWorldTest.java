import org.apache.tools.ant.BuildFileRule;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.Rule;
import org.apache.tools.ant.AntAssert;
import org.apache.tools.ant.BuildException;
import org.hamcrest.CoreMatchers;

public class HelloWorldTest {

    @Rule
    public final BuildFileRule buildRule = new BuildFileRule();

    @Before
    public void setUp() {
        // initialize Ant
        buildRule.configureProject("build.xml");
    }

    @Test
    public void testWithout() {
        buildRule.executeTarget("use.without");
        Assert.assertEquals("Message was logged but should not.", buildRule.getLog(), "");
    }

    @Test
    public void testMessage() {
        // execute target 'use.nestedText' and expect a message
        // 'attribute-text' in the log
        buildRule.executeTarget("use.message");
        Assert.assertThat(buildRule.getLog(), CoreMatchers.containsString("attribute-text"));
    }

    @Test
    public void testNestedText() {
        buildRule.executeTarget("use.nestedText");
        Assert.assertThat(buildRule.getLog(), CoreMatchers.containsString("nested-text"));
    }

    @Test
    public void testNestedElement() {
        buildRule.executeTarget("use.nestedElement");
        AntAssert.assertContains("Nested Element 1", buildRule.getLog());
        AntAssert.assertContains("Nested Element 2", buildRule.getLog());
    }
}

