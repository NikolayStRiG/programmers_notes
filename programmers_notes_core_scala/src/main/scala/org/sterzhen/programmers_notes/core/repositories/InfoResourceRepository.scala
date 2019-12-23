package org.sterzhen.programmers_notes.core.repositories

import org.sterzhen.programmers_notes.core.damain.InfoResource

/**
 * This interface is used to interact with the database
 */
trait InfoResourceRepository {

  /**
   * Find InfoResource by id in DB
   *
   * @param id InfoResource id
   * @return Option[InfoResource]
   */
  def findById(id: Long): Option[InfoResource]

  /**
   * Create new InfoResource
   *
   * @param resource new InfoResource
   */
  def insert(resource: InfoResource)

  /**
   * Update InfoResource
   *
   * @param resource InfoResource
   */
  def update(resource: InfoResource)

  /**
   * Delete InfoResource by id
   *
   * @param id InfoResource id
   */
  def delete(id: Long)

  /**
   * Return next entity id for
   *
   * @return next id
   */
  def nextEntityId(): Long

  /**
   * Exist by name
   *
   * @param name InfoResource name
   * @return true if exists there is otherwise false
   */
  def existByName(name: String): Boolean

  /**
   * Exist by address
   *
   * @param address InfoResource name
   * @return true if exists there is otherwise false
   */
  def existByAddress(address: String): Boolean
}
