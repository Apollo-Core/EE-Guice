package at.uibk.dps.ee.guice.init_term;

import at.uibk.dps.ee.core.Initializer;
import at.uibk.dps.ee.core.Terminator;

/**
 * Interface for classes which model components which require both an init and a
 * close operation (such e.g., opening and closing files)
 * 
 * @author Fedor Smirnov
 */
public interface ManagedComponent extends Initializer, Terminator {
}
