package org.sterzhen.programmers_notes.core.services
import org.sterzhen.programmers_notes.core.damain.InfoResource
import org.sterzhen.programmers_notes.core.repositories.InfoResourceRepository

/**
 * Implementation InfoResourceService
 *
 * @param infoResRepository InfoResourceRepository
 */
class InfoResourceServiceImpl(val infoResRepository: InfoResourceRepository) extends InfoResourceService {

  override def findById(id: Long): Option[InfoResource] = {
    infoResRepository.findById(id)
  }
}
