package at.uibk.dps.ee.guice.container;

import com.google.gson.JsonObject;
import at.uibk.dps.ee.core.ContainerManager;
import io.vertx.core.Future;

/**
 * {@link ContainerManagerNone} is bound in cases where no container management
 * is necessary.
 * 
 * @author Fedor Smirnov
 *
 */
public class ContainerManagerNone implements ContainerManager {

  protected static final String exceptionMessage = "Should never be called.";

  @Override
  public void initImage(final String imageName) {
    throw new IllegalStateException(exceptionMessage);
  }

  @Override
  public Future<JsonObject> runImage(final String imageName, final JsonObject functionInput) {
    throw new IllegalStateException(exceptionMessage);
  }

  @Override
  public void closeImage(final String imageName) {
    throw new IllegalStateException(exceptionMessage);
  }
}
