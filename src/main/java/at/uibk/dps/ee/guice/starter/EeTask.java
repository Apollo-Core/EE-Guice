package at.uibk.dps.ee.guice.starter;

import org.opt4j.core.start.Opt4JTask;
import com.google.gson.JsonObject;
import at.uibk.dps.ee.core.InputDataProvider;
import at.uibk.dps.ee.core.exception.FailureException;
import at.uibk.dps.ee.guice.EeCoreInjectable;

/**
 * The {@link EeTask} triggers the enactment of a workflow based on a
 * module-driven configuration of the enactment process. Used for enactments which are activated via the configuration GUI.
 * 
 * @author Fedor Smirnov
 *
 */
public class EeTask extends Opt4JTask {

  @Override
  public void execute() throws FailureException {
    open();
    EeCoreInjectable eeCore = injector.getInstance(EeCoreInjectable.class);
    InputDataProvider inputProvider =  new InputDataProvider() {
      
      @Override
      public JsonObject getInputData() {
        
        return new JsonObject();
      }
    }; //injector.getInstance(InputDataProvider.class);
    JsonObject inputData = inputProvider.getInputData();
    eeCore.enactWorkflow(inputData);
    if (closeOnStop) {
      close();
    }
  }
}
