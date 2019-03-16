import UIKit
import main

class BarbellPlatesView: UIView {
    
    private static let plateWidth: CGFloat = 20.0
    private static let plateBuffer: CGFloat = 8.0
    
    private var verticalCenter: CGFloat {
        return bounds.height / 2
    }
    
    private var plates = [Plate]()

    override func draw(_ rect: CGRect) {
        // clear out any previous draws, if needed
        let basePath = UIBezierPath(rect: rect)
        UIColor.white.setFill()
        basePath.fill()
        
        var index = 0
        plates.forEach { plate in
            let plateColor = hexStringToUIColor(hex: plate.color)
            let plateHeight = CGFloat(plate.height)
            let platePath = UIBezierPath()
            let plateX: CGFloat = CGFloat(index) * (BarbellPlatesView.plateWidth + BarbellPlatesView.plateBuffer)
            let plateY: CGFloat = verticalCenter
            
            platePath.lineWidth = plateHeight
            
            platePath.move(to: CGPoint(
                x: plateX,
                y: plateY)
            )
            
            platePath.addLine(to: CGPoint(
                x: plateX + BarbellPlatesView.plateWidth,
                y: plateY)
            )
            
            plateColor.setStroke()
            platePath.stroke()
            index += 1
        }
    }
    
    func updatePlates(plates: [Plate]) {
        self.plates = plates
        setNeedsDisplay()
    }
}
