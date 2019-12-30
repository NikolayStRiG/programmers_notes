package org.sterzhen.programmers_notes.core.services

import org.sterzhen.programmers_notes.core.damain.InfoResource

/**
 * The interface performs service operations with InfoResource
 */
trait InfoResourceService {

  /**
   * Find InfoResource by id
   *
   * @param id InfoResource id
   * @return Option[InfoResource]
   */
  def findById(id: Long): Option[InfoResource]

  /**
   * Create new InfoResource
   *
   * @param name        InfoResource name
   * @param address     InfoResource address
   * @param description InfoResource description
   */
  def create(name: String, address: String, description: String): InfoResource

  /**
   * Update InfoResource
   *
   * @param resource InfoResource
   */
  def update(resource: InfoResource): InfoResource

  /**
   * Delete InfoResource
   *
   * @param resource InfoResource
   */
  def delete(resource: InfoResource): InfoResource

  /**
   * Delete InfoResource by id
   *
   * @param id InfoResource id
   */
  def delete(id: Long): InfoResource
}
