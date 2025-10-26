"use client";

import { useEffect, useRef } from 'react';

import { Feature } from 'ol';
import { Point } from 'ol/geom';
import TileLayer from 'ol/layer/Tile';
import VectorLayer from 'ol/layer/Vector';
import Map from 'ol/Map';
import 'ol/ol.css';
import { fromLonLat } from 'ol/proj';
import OSM from 'ol/source/OSM';
import VectorSource from 'ol/source/Vector';
import { Circle as CircleStyle, Fill, Stroke, Style } from 'ol/style';
import View from 'ol/View';

type OpenLayersMapProps = {
    lat: number;
    lon: number;
    zoom?: number;
};

export default function OpenLayersMap({ lat, lon, zoom = 13 }: OpenLayersMapProps) {
    const mapRef = useRef<HTMLDivElement | null>(null);
    const mapInstanceRef = useRef<Map | null>(null);

    useEffect(() => {
        if (!mapRef.current) return;

        const position = fromLonLat([lon, lat]);

        // Base layer (OpenStreetMap)
        const baseLayer = new TileLayer({
            source: new OSM(),
        });

        // Marker
        const marker = new Feature({
            geometry: new Point(position),
        });

        marker.setStyle(
            new Style({
                image: new CircleStyle({
                    radius: 10, // raggio in pixel
                    fill: new Fill({
                        color: "#1d4e29", // colore interno
                    }),
                    stroke: new Stroke({
                        color: "#9ebb3b", // colore del bordo
                        width: 6,
                    }),
                }),
            })
        );

        const vectorLayer = new VectorLayer({
            source: new VectorSource({
                features: [marker],
            }),
        });

        // Crea la mappa
        const map = new Map({
            target: mapRef.current,
            layers: [baseLayer, vectorLayer],
            view: new View({
                center: position,
                zoom,
            }),
            controls: [],
        });

        mapInstanceRef.current = map;

        // Cleanup
        return () => {
            map.setTarget(undefined);
            mapInstanceRef.current = null;
        };
    }, [lat, lon, zoom]);

    return <div ref={mapRef} style={{ width: "100%", height: "100%", minHeight: "200px" }} />;
}
