package rover.domain

case class Rover(facing: Direction, position: Position) {

  def rotate(rotationDirection: RotationDirection): Rover =
    rotationDirection match
      case RotationDirection.Clockwise => copy(facing = facing.rotateClockwise)
      case RotationDirection.AntiClockwise => copy(facing = facing.rotateAntiClockwise)

  def move(grid: Grid): Rover =
    facing match
      case Direction.North => copy(position = position.copy(y = if ( position.y <= 0) { grid.dimensions.height - 1 } else { position.y - 1 }))
      case Direction.East => copy(position = position.copy(x = if ( position.x >= grid.dimensions.width - 1) { 0 } else { position.x + 1 }))
      case Direction.South => copy(position = position.copy(y = if ( position.y >= grid.dimensions.height - 1) { 0 } else { position.y + 1 }))
      case Direction.West => copy(position = position.copy(x = if ( position.x <= 0) { grid.dimensions.width - 1 } else { position.x - 1 }))

}


