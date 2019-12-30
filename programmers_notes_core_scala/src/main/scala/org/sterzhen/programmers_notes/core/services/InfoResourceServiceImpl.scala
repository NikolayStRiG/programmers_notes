package org.sterzhen.programmers_notes.core.services
import org.sterzhen.programmers_notes.core.damain.InfoResource
import org.sterzhen.programmers_notes.core.exceptions.EntityNotFoundException
import org.sterzhen.programmers_notes.core.repositories.InfoResourceRepository

/**
 * Implementation InfoResourceService
 *
 * @param infoResRepository InfoResourceRepository
 */
class InfoResourceServiceImpl(val infoResRepository: InfoResourceRepository) extends InfoResourceService {

  /**
   * Find InfoResource by id
   *
   * @param id InfoResource id
   * @return Option[InfoResource]
   */
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
    if (name == null || name.isEmpty) {
      throw new IllegalArgumentException("A name is empty")
    }
    if (address == null || address.isEmpty) {
      throw new IllegalArgumentException("A address is empty")
    }
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
  override def update(resource: InfoResource): InfoResource = {
    val oldOp = findById(resource.id)
    if (oldOp.isEmpty) {
      throw new EntityNotFoundException("Entity not found")
    }
    val old = oldOp.get
    if (!old.name.equals(resource.name) && infoResRepository.existByName(resource.name)) {
      throw new IllegalArgumentException("An entity with that name already exists")
    }
    if (!old.address.equals(resource.address) && infoResRepository.existByAddress(resource.address)) {
      throw new IllegalArgumentException("An entity with that address already exists")
    }
    infoResRepository.update(resource)
    resource
  }

  /**
   * Delete InfoResource
   *
   * @param resource InfoResource
   */
  override def delete(resource: InfoResource): InfoResource = {
    delete(resource.id)
  }

  /**
   * Delete InfoResource by id
   *
   * @param id InfoResource id
   */
  override def delete(id: Long): InfoResource = {
    val old = infoResRepository.findById(id)
    if (old.isEmpty) {
      throw new EntityNotFoundException("Entity not found")
    }
    infoResRepository.delete(id)
    old.get
  }
}
