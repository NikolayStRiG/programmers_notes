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
}
