package at.uibk.dps.ee.guice.container;

import com.google.inject.ImplementedBy;
import at.uibk.dps.ee.core.ContainerManager;

/**
 * The {@link ContainerManagerProvider} is used to inject the dependency to a
 * {@link ContainerManager}.
 * 
 * @author Fedor Smirnov
 *
 */
@ImplementedBy(ContainerManagerProviderNone.class)
public interface ContainerManagerProvider {

  /**
   * Returns the container manager to be used during the orchestration.
   * 
   * @return the container manager to be used during the orchestration
   */
  public ContainerManager getContainerManager();

}
