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

  /**
   * Create new InfoResource
   *
   * @param name        InfoResource name
   * @param address     InfoResource address
   * @param description InfoResource description
   */
  override def create(name: String, address: String, description: String): InfoResource = {
    if (infoResRepository.existByName(name)) {
      throw new IllegalArgumentException("An entity with that name already exists")
    }
    if (infoResRepository.existByAddress(address)) {
      throw new IllegalArgumentException("An entity with that address already exists")
    }
    val id: Long = infoResRepository.nextEntityId()
    val resource = InfoResource.of(id, name, address, description)
    infoResRepository.insert(resource)
    resource
  }

  /**
   * Update InfoResource
   *
   * @param resource InfoResource
   */
  override def update(resource: InfoResource): Unit = {
    infoResRepository.update(resource)
  }

  /**
   * Delete InfoResource
   *
   * @param resource InfoResource
   */
  override def delete(resource: InfoResource): Unit = {
    infoResRepository.delete(resource.id)
  }

  /**
   * Delete InfoResource by id
   *
   * @param id InfoResource id
   */
  override def delete(id: Long): Unit = {
    infoResRepository.delete(id)
  }
}
