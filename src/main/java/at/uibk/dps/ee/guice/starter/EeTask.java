package at.uibk.dps.ee.guice.starter;

import java.util.concurrent.CountDownLatch;
import org.opt4j.core.start.Opt4JTask;
import com.google.gson.JsonObject;
import at.uibk.dps.ee.core.InputDataProvider;
import at.uibk.dps.ee.guice.EeCoreInjectable;
import io.vertx.core.Future;

/**
 * The {@link EeTask} triggers the enactment of a workflow based on a
 * module-driven configuration of the enactment process. Used for enactments
 * which are activated via the configuration GUI.
 * 
 * @author Fedor Smirnov
 *
 */
public class EeTask extends Opt4JTask {

  @Override
  public void execute() {
    open();
    final EeCoreInjectable eeCore = injector.getInstance(EeCoreInjectable.class);
    final InputDataProvider inputProvider = injector.getInstance(InputDataProvider.class);
    final JsonObject inputData = inputProvider.getInputData();
    final Future<JsonObject> futureResult = eeCore.enactWorkflow(inputData);
    // to process the EE Task synchronously TODO think about making this optional
    final CountDownLatch latch = new CountDownLatch(1);

    futureResult.onComplete(asyncRes -> {
      latch.countDown();
      if (closeOnStop) {
        close();
      }
    });

    try {
      latch.await();
    } catch (InterruptedException e) {
      throw new IllegalStateException("CD Latch in the EETask interrupted.", e);
    }
  }
}
