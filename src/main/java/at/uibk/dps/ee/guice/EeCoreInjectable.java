package at.uibk.dps.ee.guice;

import java.util.Set;

import com.google.inject.Inject;
import at.uibk.dps.ee.core.CoreFunction;
import at.uibk.dps.ee.core.EeCore;
import at.uibk.dps.ee.core.LocalResources;
import at.uibk.dps.ee.core.OutputDataHandler;
import at.uibk.dps.ee.core.function.EnactmentStateListener;

/**
 * Class definition with the inject annotation (Apart from that identical with
 * {@link EeCore}).
 * 
 * @author Fedor Smirnov
 *
 */
public class EeCoreInjectable extends EeCore {

  /**
   * This is the constructor which will be used during the dynamic dependency
   * injection.
   * 
   * @param outputDataHandler the component handling the data produced when
   *        running the workflow
   * @param enactableProvider the component providing the root enactable which is
   *        used to run the enactment
   * @param stateListeners the listeners which react to transitions between
   *        different states of the enactment
   * @param localResources interfaces for the configuration of the local resources
   */
  @Inject
  public EeCoreInjectable(final OutputDataHandler outputDataHandler,
      final Set<EnactmentStateListener> stateListeners,
      final Set<LocalResources> localResources, CoreFunction coreFunction) {
    super(outputDataHandler, stateListeners, localResources, coreFunction);
  }
}
