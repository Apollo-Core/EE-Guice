package at.uibk.dps.ee.guice.init;

import at.uibk.dps.ee.guice.starter.VertxProvider;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.Message;

/**
 * An {@link InitializerComponent} is triggered by an ebus event, performs the
 * initialization, and then sends a response over the event bus.
 * 
 * @author Fedor Smirnov
 *
 */
public abstract class InitializerComponent {

  protected final String listenAddress;
  protected final String readySignal;
  protected final Vertx vertX;

  /**
   * The constructor
   * 
   * @param vProv vertX provider to get the EBus
   * @param listenAddress the address to listen for the start signal
   * @param writeAddress the address to write the ready signal
   */
  public InitializerComponent(VertxProvider vProv, String listenAddress, String readySignal) {
    this.vertX = vProv.getVertx();
    this.readySignal = readySignal;
    this.listenAddress = listenAddress;
    vertX.eventBus().consumer(listenAddress, this::triggerInitialization);
  }

  public String getListenAddress() {
    return listenAddress;
  }

  public String getReadySignal() {
    return readySignal;
  }

  /**
   * Triggers the async initialization and sends the ready signal when it is
   * completed
   * 
   * @param triggerMessage the message triggering the init.
   */
  protected final void triggerInitialization(Message<String> triggerMessage) {
    actualInitialization().onComplete(asyncRes -> {
      vertX.eventBus().send(InitializerSet.INIT_SET_ADDRESS, readySignal);
    });
  }

  /**
   * Asynchronously performs the initialization step.
   * 
   * @return a future which is completed when the initialization step is finished
   */
  protected abstract Future<String> actualInitialization();
}
