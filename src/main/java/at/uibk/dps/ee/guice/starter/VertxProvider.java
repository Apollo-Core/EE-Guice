package at.uibk.dps.ee.guice.starter;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.ext.web.client.WebClient;

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

  /**
   * Creates a provider which provides access to an existing VertX instance.
   * 
   * This constructor is used to manually instantiate a provider to a given vertX
   * instance.
   * 
   * @param vertx the existing vertx instance
   */
  public VertxProvider(final Vertx vertx) {
    this.vertx = vertx;
    vertx.exceptionHandler(throwable -> {
      throwable.printStackTrace();
    });
    this.eBus = vertx.eventBus();
  }

  /**
   * Creates a vertx instances which is used to provide a unified context for all
   * verticles which are created by the classes built by the current injector.
   * 
   * This constructor is used when the {@link VertxProvider} instance is
   * instantiated by Guice.
   */
  @Inject
  public VertxProvider() {
    this.vertx = Vertx.vertx();
    vertx.exceptionHandler(throwable -> {
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

  /**
   * Returns a web client running on the vertX instance configured for this
   * provider.
   * 
   * @return a web client running on the vertX instance configured for this
   *         provider.
   */
  public WebClient getWebClient() {
    return WebClient.create(vertx);
  }
}
