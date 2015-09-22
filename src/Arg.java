import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.Option;
/**
 * Created by davidzd on 15/9/20.
 */
public class Arg {
    @Argument
    public String remoteServer;
    @Option(name="-p", usage = "Port")
    public int port = 4444;
}
