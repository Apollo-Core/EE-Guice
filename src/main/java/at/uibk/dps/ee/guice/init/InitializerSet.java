package at.uibk.dps.ee.guice.init;

import java.util.HashSet;
import java.util.Set;
import com.google.inject.Inject;
import at.uibk.dps.ee.core.Initializer;
import at.uibk.dps.ee.guice.starter.VertxProvider;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;

public class InitializerSet implements Initializer {

  protected static final String INIT_SET_ADDRESS = "initSet";

  protected final Set<String> componentAddresses;
  protected final Set<String> unfinished;
  protected final Promise<String> resultPromise;
  protected final EventBus eBus;

  @Inject
  public InitializerSet(Set<InitializerComponent> initComponents, VertxProvider vProv) {
    this.eBus = vProv.geteBus();
    this.unfinished = new HashSet<>();
    initComponents.forEach(initComp -> unfinished.add(initComp.getReadySignal()));
    componentAddresses = new HashSet<>();
    initComponents.forEach(initComp -> componentAddresses.add(initComp.getListenAddress()));
    this.resultPromise = Promise.promise();
    eBus.consumer(INIT_SET_ADDRESS, this::readySignalHandler);
    checkFinished();
  }

  protected void checkFinished() {
    if (unfinished.isEmpty()) {
      resultPromise.complete("init done");
    }
  }

  protected void readySignalHandler(Message<String> readySignal) {
    String signal = readySignal.body();
    if (unfinished.contains(signal)) {
      unfinished.remove(signal);
      checkFinished();
    } else {
      throw new IllegalStateException("Unexpected ready signal");
    }
  }

  @Override
  public Future<String> initialize() {
    componentAddresses.forEach(address -> eBus.send(address, "start init"));
    return resultPromise.future();
  }
}
