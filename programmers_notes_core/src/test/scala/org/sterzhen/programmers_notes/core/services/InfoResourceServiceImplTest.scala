package org.sterzhen.programmers_notes.core.services

import org.junit.jupiter.api._
import org.junit.jupiter.api.Assertions._
import org.mockito.quality.Strictness
import org.mockito._
import org.mockito.Mockito._
import org.mockito.ArgumentMatchers._
import org.sterzhen.programmers_notes.core.damain.InfoResource
import org.sterzhen.programmers_notes.core.repositories.InfoResourceRepository

class InfoResourceServiceImplTest {

  @Mock
  var infoResRepository: InfoResourceRepository = _

  var session: MockitoSession = _

  @BeforeEach
  def setUp(): Unit = {
    session = mockitoSession.initMocks(this).strictness(Strictness.LENIENT).startMocking
  }

  @AfterEach
  def tearDown(): Unit = {
    session.finishMocking()
  }

  @Test
  def findByIdFoundTest(): Unit = {
    val service = new InfoResourceServiceImpl(infoResRepository)

    val id: Long = 1
    when(infoResRepository.findById(id)).thenReturn(Option.apply(new InfoResource(id, "", "", "")))

    val result = service.findById(id)

    assertTrue(result.nonEmpty)
    val entity: InfoResource = result.get
    assertEquals(id, entity.id)
  }

  @Test
  def findByIdNotFoundTest(): Unit = {
    val service = new InfoResourceServiceImpl(infoResRepository)

    val id: Long = 1
    when(infoResRepository.findById(id)).thenReturn(Option.empty)

    val result = service.findById(id)

    assertTrue(result.isEmpty)
  }

  @Test
  def createTest(): Unit = {
    val service = new InfoResourceServiceImpl(infoResRepository)
    val id: Long = 2
    val name: String = "name"
    val address: String = "address"
    val description: String = "description"

    when(infoResRepository.nextEntityId()).thenReturn(id)

    val result = service.create(name, address, description)

    assertEquals(id, result.id)
    assertEquals(name, result.name)
    assertEquals(address, result.address)
    assertEquals(description, result.description)

    verify(infoResRepository, times(1)).nextEntityId()
    verify(infoResRepository, times(1)).insert(any())
  }

  @Test
  def createIllegalArgumentExceptionByNameTest(): Unit = {
    val service = new InfoResourceServiceImpl(infoResRepository)
    val name: String = "name"
    val address: String = "address"
    val description: String = "description"

    when(infoResRepository.existByName(name)).thenReturn(true)

    assertThrows(classOf[IllegalArgumentException], () => {
      service.create(name, address, description)
    })
  }

  @Test
  def createIllegalArgumentExceptionByAddressTest(): Unit = {
    val service = new InfoResourceServiceImpl(infoResRepository)
    val name: String = "name"
    val address: String = "address"
    val description: String = "description"

    when(infoResRepository.existByAddress(address)).thenReturn(true)

    assertThrows(classOf[IllegalArgumentException], () => {
      service.create(name, address, description)
    })
  }

  @Test
  def createNameEmptyTest(): Unit = {
    val service = new InfoResourceServiceImpl(infoResRepository)
    val name: String = ""
    val address: String = "address"
    val description: String = "description"

    assertThrows(classOf[IllegalArgumentException], () => {
      service.create(null, address, description)
    })

    assertThrows(classOf[IllegalArgumentException], () => {
      service.create(name, address, description)
    })
  }

  @Test
  def createAddressEmptyTest(): Unit = {
    val service = new InfoResourceServiceImpl(infoResRepository)
    val name: String = "name"
    val address: String = ""
    val description: String = "description"

    assertThrows(classOf[IllegalArgumentException], () => {
      service.create(name, null, description)
    })

    assertThrows(classOf[IllegalArgumentException], () => {
      service.create(name, address, description)
    })
  }
}
