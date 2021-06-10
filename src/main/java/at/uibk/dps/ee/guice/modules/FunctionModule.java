package at.uibk.dps.ee.guice.modules;

import org.opt4j.core.config.annotations.Category;
import com.google.inject.Binder;
import com.google.inject.multibindings.Multibinder;
import at.uibk.dps.ee.core.enactable.FunctionDecoratorFactory;

/**
 * Parent class for the modules configuring the enactment functions.
 * 
 * @author Fedor Smirnov
 */
@Category("Functions")
public abstract class FunctionModule extends EeModule {

  /**
   * Adds a function decorator which is to be used when creating functions.
   * 
   * @param functionDecorator the decorator to add
   */
  public void addFunctionDecoratorFactory(
      final Class<? extends FunctionDecoratorFactory> functionDecorator) {
    addFunctionDecoratorFactory(binder(), functionDecorator);
  }

  /**
   * Adds a function decorator which is to be used when creating functions.
   * 
   * @param binder the binder
   * @param functionDecorator the decorator to add
   */
  public static void addFunctionDecoratorFactory(final Binder binder,
      final Class<? extends FunctionDecoratorFactory> functionDecorator) {
    final Multibinder<FunctionDecoratorFactory> multiBinder =
        Multibinder.newSetBinder(binder, FunctionDecoratorFactory.class);
    multiBinder.addBinding().to(functionDecorator);
  }
}
