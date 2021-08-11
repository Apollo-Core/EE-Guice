package at.uibk.dps.ee.guice.container;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import at.uibk.dps.ee.core.ContainerManager;

/**
 * Default {@link ContainerManagerProvider}. Used if no containers necessary.
 * Throws an exception if asked for container manager.
 * 
 * @author Fedor Smirnov
 */
@Singleton
public class ContainerManagerProviderNone implements ContainerManagerProvider {

  protected final ContainerManagerNone containerManager;
  
  /**
   * Injection constructor
   */
  @Inject
  public ContainerManagerProviderNone() {
    this.containerManager = new ContainerManagerNone();
  }
  
  @Override
  public ContainerManager getContainerManager() {
    return this.containerManager;
  }
}
