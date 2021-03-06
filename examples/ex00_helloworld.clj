;; Complete minimal example

(ns examples.ex00-helloworld
  (:require [clojure2d.core :refer :all]
            [clojure2d.math :as m]
            [clojure2d.color :as c]))

(set! *warn-on-reflection* true)
(set! *unchecked-math* :warn-on-boxed)

;; define canvas
(def canvas (make-canvas 600 600))

;; create window
(def window (show-window canvas "Hello World!"))

;; draw rectangle with line
(with-canvas canvas ;; prepare drawing context in canvas
  (set-background 10 5 5) ;; clear background
  (set-color 210 210 200) ;; set color
  (rect 100 100 400 400) ;; draw rectangle
  (set-color 50 50 60) ;; set another color
  (set-stroke 2.0) ;; set line width
  (line 50 300 550 300)) ;; draw line

;; now lets define drawing function
;; type hints to avoid boxed operations
(defn draw
  "Draw rotating rectangle. This function is prepared to be run in refreshing thread from your window."
  [canvas ;; canvas to draw on
   window ;; window bound to function (for mouse movements)
   ^long framecount ;; frame number
   state] ;; state (if any), not used here
  (let [midwidth (* 0.5 ^long (width canvas))] ;; find middle of the canvas

    (-> canvas ;; use canvas (context is already ready! It's draw function.)
        (set-background :linen) ;; clear background with :inen color
        (translate midwidth midwidth) ;; set origin in the middle
        (rotate (/ framecount 100.0)) ;; rotate clockwise (based on number of frame)
        (set-color :maroon) ;; set color to maroon
        (crect 0 0 midwidth midwidth) ;; draw centered rectangle
        (rotate (/ framecount -90.0)) ;; rotate counterclockwise
        (set-color 255 69 0 200) ;; set color to orange with transparency
        (crect 0 0 (* 0.9 midwidth) (* 0.9 midwidth))))) ;; draw smaller rectangle

;; run twice!
(show-window (make-canvas 600 600) "Rotating square" draw) ;; create canvas, display window and draw on canvas via draw function (60 fps)
