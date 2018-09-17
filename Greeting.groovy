@RestController
class Grreeter {
  
  @GetMapping('/greet')
  def sayHello() {
    return 'hello'
  }
}
