package suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses(
{
    net.TestTCPServer.class,
    net.TestTCPClient.class,
    net.TestWorker.class,
    net.TestUDPServer.class,
    net.TestUDPClient.class
})
public class TestSuite {}
