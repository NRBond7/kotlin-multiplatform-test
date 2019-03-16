import UIKit
import main

class BarbellPlatesView: UIView {
    
    private static let plateWidth: CGFloat = 20.0
    private static let plateBufferSpace: CGFloat = 8.0
    
    private var verticalCenter: CGFloat {
        return bounds.height / 2
    }
    
    private var plates = [Plate]()

    override func draw(_ rect: CGRect) {
        let basePath = UIBezierPath(rect: rect)
        UIColor.white.setFill()
        basePath.fill()
        
        var index = 0
        plates.forEach { plate in
            let plateColor = hexStringToUIColor(hex: plate.color)
            let plateHeight = CGFloat(plate.height)
            let platePath = UIBezierPath()
            let plateX: CGFloat = CGFloat(index) * (BarbellPlatesView.plateWidth + BarbellPlatesView.plateBufferSpace)
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
            
            let paragraphStyle = NSMutableParagraphStyle()
            paragraphStyle.alignment = .center
            
            let attributes: [NSAttributedString.Key : Any] = [
                .paragraphStyle: paragraphStyle,
                .font: UIFont.systemFont(ofSize: 14.0),
                .foregroundColor: UIColor.black
            ]
            
            let plateText: String
            if Double(Int(plate.weight)) == plate.weight {
                plateText = String(Int(plate.weight))
            } else {
                plateText = String(plate.weight)
            }
            let attributedString = NSAttributedString(string: plateText, attributes: attributes)
            
            let stringRect = CGRect(x: plateX, y: bounds.height - 40, width: BarbellPlatesView.plateWidth, height: 40)
            attributedString.draw(in: stringRect)
            
            index += 1
        }
    }
    
    func updatePlates(plates: [Plate]) {
        self.plates = plates
        setNeedsDisplay()
    }
}
