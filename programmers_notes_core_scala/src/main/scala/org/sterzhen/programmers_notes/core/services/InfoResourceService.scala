package org.sterzhen.programmers_notes.core.services

import org.sterzhen.programmers_notes.core.damain.InfoResource

trait InfoResourceService {

  def findById(id: Long): Option[InfoResource]
}
