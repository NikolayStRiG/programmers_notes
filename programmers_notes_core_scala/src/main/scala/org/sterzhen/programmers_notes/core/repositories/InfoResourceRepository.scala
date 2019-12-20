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
}
