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
  protected final WebClient webClient;

  /**
   * Creates a vertx instances which is used to provide a unified context for all
   * verticles which are created by the classes built by the current injector.
   */
  @Inject
  public VertxProvider() {
    this.vertx = Vertx.vertx();
    vertx.exceptionHandler(throwable -> {
      throwable.printStackTrace();
    });
    this.eBus = vertx.eventBus();
    this.webClient = WebClient.create(vertx);
  }

  public Vertx getVertx() {
    return vertx;
  }

  public EventBus geteBus() {
    return eBus;
  }

  public WebClient getWebClient() {
    return webClient;
  }
}
