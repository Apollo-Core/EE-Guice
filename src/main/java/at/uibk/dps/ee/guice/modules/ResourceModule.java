package at.uibk.dps.ee.guice.modules;

import org.opt4j.core.config.annotations.Category;
import com.google.inject.Binder;
import at.uibk.dps.ee.core.LocalResources;

/**
 * Parent class for the modules configuring the local resources.
 * 
 * @author Fedor Smirnov
 */
@Category("Resources")
public abstract class ResourceModule extends EeModule {

  /**
   * Adds a {@link LocalResources} interface for local resource configuration.
   * 
   * @param binder the binder
   * @param localResources the interface to the local resources
   */
  public static void addLocalResources(final Binder binder,
      final Class<? extends LocalResources> localResources) {
  }
  
  /**
   * Adds a {@link LocalResources} interface for local resource configuration.
   * 
   * @param localResources the interface to the local resources
   */
  public void addLocalResources(final Class<? extends LocalResources> localResources) {
    addLocalResources(binder(), localResources);
  }
}
