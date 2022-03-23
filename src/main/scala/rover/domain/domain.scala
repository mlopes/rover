package rover.domain

enum Direction:
  case North extends Direction
  case East extends Direction
  case South extends Direction
  case West extends Direction

  def rotateClockwise: Direction =
    this match {
      case North => East
      case East => South
      case South => West
      case West => North
    }

  def rotateAntiClockwise: Direction =
    this match {
      case North => West
      case East => North
      case South => East
      case West => South
    }




enum RotationDirection:
  case Clockwise extends RotationDirection
  case AntiClockwise extends RotationDirection


final case class Position(x: Int, y: Int)
