package com.cognizant.mockito.api;

/**
 * ExternalApi represents a third-party / external service that MyService
 * depends on. In real-world applications this interface would typically
 * be backed by a REST client, a database gateway, or some other remote
 * integration.
 *
 * For the purpose of these Mockito hands-on exercises, this interface is
 * never implemented directly by production code that is under test -
 * instead it is mocked using Mockito so that MyService can be tested in
 * complete isolation from any real external dependency.
 */
public interface ExternalApi {

    /**
     * Fetches a single piece of data from the external system.
     *
     * @return the data returned by the external system
     */
    String getData();

    /**
     * Fetches data associated with a specific identifier from the
     * external system.
     *
     * @param id the identifier used to look up the data
     * @return the data associated with the given id
     */
    String getDataById(int id);

    /**
     * Saves/persists data to the external system. This is a void method
     * used to demonstrate stubbing and verifying void interactions with
     * Mockito (doNothing(), doThrow(), verify()).
     *
     * @param data the data to save
     */
    void saveData(String data);

    /**
     * Opens a connection to the external system. Used together with
     * {@link #getData()} and {@link #disconnect()} to demonstrate
     * verifying interaction order using Mockito's InOrder API.
     */
    void connect();

    /**
     * Closes the connection to the external system.
     */
    void disconnect();

    /**
     * Returns the current status of the external system. Used to
     * demonstrate stubbing multiple consecutive return values using
     * chained thenReturn() calls.
     *
     * @return the current status as a String
     */
    String getStatus();
}
