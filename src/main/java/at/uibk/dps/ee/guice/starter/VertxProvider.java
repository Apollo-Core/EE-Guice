package at.uibk.dps.ee.guice.starter;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;

/**
 * Injectable class to provide the current VertX instance and the event bus.
 * 
 * @author Fedor Smirnov
 *
 */
@Singleton
public class VertxProvider {

  protected final Vertx vertx;
  protected final EventBus eBus;
  
  @Inject
  public VertxProvider() {
    this.vertx = Vertx.vertx();
    vertx.exceptionHandler(throwable -> {
      System.err.println("Exception");
      throwable.printStackTrace();
    });
    this.eBus = vertx.eventBus();
  }

  public Vertx getVertx() {
    return vertx;
  }

  public EventBus geteBus() {
    return eBus;
  }
}
