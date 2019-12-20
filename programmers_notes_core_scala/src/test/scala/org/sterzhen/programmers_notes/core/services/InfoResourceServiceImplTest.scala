package org.sterzhen.programmers_notes.core.services

import org.junit.jupiter.api._
import org.junit.jupiter.api.Assertions._
import org.mockito.quality.Strictness
import org.mockito._
import org.mockito.Mockito._
import org.sterzhen.programmers_notes.core.damain.InfoResource
import org.sterzhen.programmers_notes.core.repositories.InfoResourceRepository

class InfoResourceServiceImplTest {

  @Mock
  var infoResService: InfoResourceRepository = _

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
    val service = new InfoResourceServiceImpl(infoResService)

    val id:Long = 1
    when(infoResService.findById(id)).thenReturn(Option.apply(new InfoResource(id, "", "", "")))

    val result = service.findById(id)

    assertTrue(result.nonEmpty)
    val entity: InfoResource = result.get
    assertEquals(id, entity.id)
  }

  @Test
  def findByIdNotFoundTest(): Unit = {
    val service = new InfoResourceServiceImpl(infoResService)

    val id:Long = 1
    when(infoResService.findById(id)).thenReturn(Option.empty)

    val result = service.findById(id)

    assertTrue(result.isEmpty)
  }
}
