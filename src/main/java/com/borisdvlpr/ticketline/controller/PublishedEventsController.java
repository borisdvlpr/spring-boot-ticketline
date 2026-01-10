package com.borisdvlpr.ticketline.controller;

import com.borisdvlpr.ticketline.domain.dto.ListPublishedEventsResponseDto;
import com.borisdvlpr.ticketline.mapper.EventMapper;
import com.borisdvlpr.ticketline.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/published-events")
@RequiredArgsConstructor
public class PublishedEventsController {
    private final EventService eventService;
    private final EventMapper eventMapper;

    @GetMapping
    public ResponseEntity<Page<ListPublishedEventsResponseDto>> listPublishedEvents(Pageable pageable) {
        return ResponseEntity.ok(
                eventService.listPublishedEvents(pageable).map(eventMapper::toListPublishedEventsResponseDto));
    }
}
