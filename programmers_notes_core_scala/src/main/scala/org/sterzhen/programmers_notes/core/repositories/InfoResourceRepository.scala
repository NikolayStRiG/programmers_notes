package org.sterzhen.programmers_notes.core.repositories

import org.sterzhen.programmers_notes.core.damain.InfoResource

trait InfoResourceRepository {

  def findById(id: Long): Option[InfoResource]
}
