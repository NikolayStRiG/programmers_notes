package org.sterzhen.programmers_notes.core.damain

/**
 * This entity encapsulates information about the resource
 *
 * @param id          resource id in DB
 * @param name        resource name
 * @param address     resource URL
 * @param description resource description
 */
class InfoResource(val id: Long, val name: String, val address: String, val description: String) {

    override def toString = s"InfoResource($id, $name, $address, $description)"
}

object InfoResource {
    def of(id: Long, name: String, address: String, description: String): InfoResource = {
        new InfoResource(id, name, address, description)
    }
}
